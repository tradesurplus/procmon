package procmon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author John Carruthers
 */
public class ProcMon {
    
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        int objects;
        int objectCount = 0;
        
        // check for user-defined XML config file
        String configFile;
        if (args.length > 0) {
            configFile = args[0];
        } else {
            configFile = "procMonConfig.xml";
        }
        
        // ensure this server has been defined in the config file
        Server s = new Server();
        String hostname = s.getHostname();
        String searchForHostname = "count(//server[hostname='" + hostname + "']) >0";
        QueryXMLfile qxf = new QueryXMLfile();
        if (!qxf.queryServerConfig(configFile, searchForHostname)) {
            System.out.println("This host (" + hostname + ") has not been defined in the config file.\nUpdate the file with the processes that need to be monitored on this server and try again.");
            System.exit(1);
        }
        
        // generate report header
        // get header components
        // based on server's hostname, get server type from XML file
        String searchForServerType = "//server[hostname='" + hostname + "']/@type";
        String serverType = qxf.query(configFile, searchForServerType);
        
        // based on server type, get header detail from XML file
        String searchForHeaderDetail = "//header[name='" + serverType + "']/header_detail/text()";
        String headerDetail = qxf.query(configFile, searchForHeaderDetail);
        
        // based on server hostname, get server description
        String searchForServerDescription = "//server[hostname='" + hostname + "']/description/text()";
        String serverDescription = qxf.query(configFile, searchForServerDescription);
        s.setDescription(serverDescription);
        
        // based on the server type, find the processes that need to be monitored
        //String searchForProcesses = "//process[@type='" + serverType + "']/search_string/text()|//process[@type='" + serverType + "']/pid_filename/text()";
        List<String> processesByString = new ArrayList<String>();
        List<String> processesByPID = new ArrayList<String>();
        String searchForProcessesByString = "//process[@type='" + serverType + "']/search_string/text()";
        String searchForProcessesByPID = "//process[@type='" + serverType + "']/pid_filename/text()";
        for (String process: qxf.queryProcesses(configFile, searchForProcessesByString)) {
            processesByString.add(process);
        }
        for (String process: qxf.queryProcesses(configFile, searchForProcessesByPID)) {
            processesByPID.add(process);
        }
        
        // work out how many Process objects to create
        objects = processesByString.size() + processesByPID.size();
        
        Process[] processToMonitor = new Process[objects];

        for (String process: processesByString) {
            //find process description
            String searchForProcessDescription = "//process[search_string='" + process + "']/description/text()";
            String processDescription = qxf.query(configFile, searchForProcessDescription);

            //find process owner
            String searchForProcessOwner = "//process[search_string='" + process + "']/owner/text()";
            String processOwner = qxf.query(configFile, searchForProcessOwner);

            //find process group
            String searchForProcessGroup = "//process[search_string='" + process + "']/@group";
            String processGroup = qxf.query(configFile, searchForProcessGroup);
            
            //create Process object
            processToMonitor[objectCount] = new ProcessByString(process, processDescription, processOwner, processGroup, null, false, false);
//            processes[objectCount] = new ProcessByString(process, processDescription, processOwner, processGroup, false);
            
            // increment var for next object
            objectCount++;
        }

        for (String process: processesByPID) {
            //set description
            String searchForProcessDescription = "//process[pid_filename='" + process + "']/description/text()";
            String processDescription = qxf.query(configFile, searchForProcessDescription);
            
            //find process owner
            String searchForProcessOwner = "//process[pid_filename='" + process + "']/owner/text()";
            String processOwner = qxf.query(configFile, searchForProcessOwner);
            
            //find process group
            String searchForProcessGroup = "//process[pid_filename='" + process + "']/@group";
            String processGroup = qxf.query(configFile, searchForProcessGroup);
            
            //create Process object
            processToMonitor[objectCount] = new ProcessByPID(process, processDescription, processOwner, processGroup, null, false, true);
            
            // increment var for next object
            objectCount++;
        }

        // take the processes that need to be monitored and look for them in a list of 
        // running processes

        // get the command to list running processes from the config file
        String searchForPSCommand = "//ps_command/text()";
        String psCommand = qxf.query(configFile, searchForPSCommand);
        ProcessList pl = new ProcessList(psCommand);
        
        // get a list of currently running processes
        List<String> AllRunningProcs = pl.runningProcs();
        
        // check whether the processes that need to be monitored are running
        ProcessSearch ps = new ProcessSearch(processToMonitor, AllRunningProcs);
        Process[] foundRunningProcs = ps.searchForProcess();
        
        List<Process> sortedByGroup = new ArrayList<Process>();
        for (Process process: foundRunningProcs) {
            sortedByGroup.add(process);
        }
        
        // sort processes by group so they can be displayed together
        Collections.sort(sortedByGroup);
        
        //get the process details we want to display
        ReportLine[] reportlines = new ReportLine[sortedByGroup.size()];
        final int PID = 0;
        final int OWNER = 1;
        final int RUNNINGTIME = 2;
        
        for (Process process : sortedByGroup) {
            if (process.getIsRunning()) {
                ProcessDetails pd = new ProcessDetails(process.getProcessDetails());
                String[] processDetails = pd.processDetails().split("\\s+");
                reportlines[sortedByGroup.indexOf(process)] = new ReportLine(process.getGroup(), process.getDescription(), process.getIsRunning(), processDetails[OWNER], process.getOwner(), processDetails[PID], processDetails[RUNNINGTIME]);
            } else {
                reportlines[sortedByGroup.indexOf(process)] = new ReportLine(process.getGroup(), process.getDescription(), process.getIsRunning(), process.getOwner(), null, null, null);
            }
        }
        
        // create a report object to store output of process details
        ReportHeader rh = new ReportHeader(headerDetail, hostname, s.getDescription());
        Report report = new Report(rh.headerText(), configFile, reportlines);
        report.printReport();
    }
}