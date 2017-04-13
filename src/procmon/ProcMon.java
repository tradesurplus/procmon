package procmon;

import java.io.IOException;
import java.util.ArrayList;
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
        
        // look up the separator char
        String searchForSeparatorChar = "//separator_char/text()";
        String separatorChar = qxf.query(configFile, searchForSeparatorChar);
        
        // output header
        ReportHeader rh = new ReportHeader();
        rh.setHeaderDetail(headerDetail);
        rh.setHostname(hostname);
        rh.setSeparatorChar(separatorChar);

        String headerText = String.format("%s        %s        %s (%s)",rh.getHeaderDate(), rh.getHeaderDetail(), rh.getHostname(), s.getDescription());
        StringLength sl = new StringLength(0);
        if (headerText.length() > sl.getHighestCharCount()) {
            sl.setHighestCharCount(headerText);
        }
        System.out.println(headerText);
        System.out.println(rh.getReportLineSeparator(sl.getHighestCharCount()));
        
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

        //debug
        for (String process: processesByString) {
            System.out.println("Monitor -> " +process);
        }
        for (String process: processesByPID) {
            System.out.println("Monitor -> " +process);
        }
        //debug
        
        
        // take the processes that need to be monitored and look for them in a list of 
        // running processes

        // get the command to list running processes from the config file
        String searchForPSCommand = "//ps_command/text()";
        String psCommand = qxf.query(configFile, searchForPSCommand);
        ProcessList pl = new ProcessList();
        pl.setPSCommand(psCommand);
        
        // get a list of currently running processes
        List<String> AllRunningProcs = pl.getRunningProcs();
        
        // check whether the processes that need to be monitored are running
        ProcessIsRunningSearch prv = new ProcessIsRunningSearch(processesByString, processesByPID, AllRunningProcs);
        List<String> foundRunningProcs = prv.searchForProcess();
        
        //debug
        for (String process: foundRunningProcs) {
            System.out.println("Running -> " +process);
        }
        //debug

        
        ProcessDetails pd = new ProcessDetails(foundRunningProcs);
        //debug
        for (String process: pd.getProcessDetails()) {
            // use split() to put each element of the string into an array
            // so that each element can be handled separately
            System.out.println("ProcessDetails -> " + process);
        }
        //debug
    }
}