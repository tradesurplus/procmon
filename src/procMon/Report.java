package procMon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.configuration2.ex.ConfigurationException;

/**
 *
 * @author John Carruthers
 */
public class Report {
    private String reportHeader;
//    private String configFile;
    private final XMLConfigFile xf;
    private final Server s;
    private int highestCharCount;

    public Report(XMLConfigFile xf, Server s) {
//        this.configFile = configFile;
        this.xf = xf;
        this.s = s;
    }
    
    private String unescape(String s) {
        int i=0,len=s.length();
        char c;
        StringBuilder sb = new StringBuilder(len);
        while (i<len) {
            c = s.charAt(i++);
            if (c=='\\') {
                if (i<len) {
                    c = s.charAt(i++);
                    if (c=='u') {
                        c = (char)Integer.parseInt(s.substring(i,i+4),16);
                        i += 4;
                    } // add other cases here as desired...
                }
            } // fall through: \ escapes itself, quotes any character but u
            sb.append(c);
        }
        return sb.toString();
    }
    
//    private String printReportGetServerType() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
//        // based on server's hostname, get server type from XML file
//        String searchForServerType = "//server[hostname='" + s.getHostname() + "']/@type";
//        String serverType = cf.query(configFile, searchForServerType);
//        //s.setType(serverType);
//        return serverType;
//    }
    
//    private String printReportGetServerDescription() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
//        // based on server hostname, get server description
//        String searchForServerDescription = "//server[hostname='" + s.getHostname() + "']/description/text()";
//        String serverDescription = cf.query(configFile, searchForServerDescription);
//        s.setDescription(serverDescription);
//        return serverDescription;
//    }
    
    private String printReportGetHeaderDetails() throws ConfigurationException {
        // based on server type, get header detail from XML file
//        String searchForHeaderDetail = "//header[name='" + s.searchForServerType(xf) + "']/header_detail/text()";
        //String searchForHeaderDetail = "//header[name='" + printReportGetServerType() + "']/header_detail/text()";
        String searchForHeaderDetail = s.searchForServerType(xf);
        String headerDetail = xf.lookupConfigFile("header", searchForHeaderDetail);
        return headerDetail;
    }
    
    private List<String> printReportGetProcessListByString() throws ConfigurationException {
        // based on the server type, find the processes that need to be monitored
        //String searchForProcesses = "//process[@type='" + serverType + "']/search_string/text()|//process[@type='" + serverType + "']/pid_filename/text()";
        List<String> processesByString = new ArrayList<>();
        String searchForProcessesByString = s.searchForServerType(xf);
        processesByString = xf.lookupProcessDetails("byString", searchForProcessesByString);
//        String searchForProcessesByString = "//process[@type='" + s.searchForServerType(xf) + "']/search_string/text()";
        //String searchForProcessesByString = "//process[@type='" + printReportGetServerType() + "']/search_string/text()";
//        for (String process:xf.lookupConfigFile("processesByString", searchForProcessesByString)) {
//            processesByString.add(process);
//        }
        return processesByString;
    }
    
    private List<String> printReportGetProcessListByPID() throws ConfigurationException {
        // based on the server type, find the processes that need to be monitored
        //String searchForProcesses = "//process[@type='" + serverType + "']/search_string/text()|//process[@type='" + serverType + "']/pid_filename/text()";
        List<String> processesByPID = new ArrayList<>();
        String searchForProcessesByPID = s.searchForServerType(xf);
        processesByPID = xf.lookupProcessDetails("byPID", searchForProcessesByPID);
        //String searchForProcessesByPID = "//process[@type='" + printReportGetServerType() + "']/pid_filename/text()";
//        for (String process:xf.queryProcesses(configFile, searchForProcessesByPID)) {
//            processesByPID.add(process);
//        }
        return processesByPID;
    }
    
    private int printReportGetHighestCharCount() {
        return highestCharCount;
    }
    
    private int printReportSetHighestCharCount(String text) {
        highestCharCount = text.length();
        return highestCharCount;
    }
    
    public void printReport() throws ConfigurationException {
        int objects;
        int objectCount = 0;
        
        // generate report header
        // get header components
        // based on server's hostname, get server type from XML file
//        Server s = new Server();
//        String hostname = s.getHostname();
//        ConfigFile cf = new ConfigFile(configFile);
//        String searchForServerType = "//server[hostname='" + hostname + "']/@type";
//        String serverType = cf.query(configFile, searchForServerType);
        
        // based on server type, get header detail from XML file
//        String searchForHeaderDetail = "//header[name='" + serverType + "']/header_detail/text()";
//        String headerDetail = cf.query(configFile, searchForHeaderDetail);
        
        // based on server hostname, get server description
//        String searchForServerDescription = "//server[hostname='" + hostname + "']/description/text()";
//        String serverDescription = cf.query(configFile, searchForServerDescription);
//        s.setDescription(serverDescription);
        
        // based on the server type, find the processes that need to be monitored
        //String searchForProcesses = "//process[@type='" + serverType + "']/search_string/text()|//process[@type='" + serverType + "']/pid_filename/text()";
//        List<String> processesByString = new ArrayList<>();
//        List<String> processesByPID = new ArrayList<>();
//        String searchForProcessesByString = "//process[@type='" + serverType + "']/search_string/text()";
//        String searchForProcessesByPID = "//process[@type='" + serverType + "']/pid_filename/text()";
//        for (String process: cf.queryProcesses(configFile, searchForProcessesByString)) {
//            processesByString.add(process);
//        }
//        for (String process: cf.queryProcesses(configFile, searchForProcessesByPID)) {
//            processesByPID.add(process);
//        }
        
        // work out how many Process objects to create
        objects = printReportGetProcessListByString().size() + printReportGetProcessListByPID().size();
        //objects = processesByString.size() + processesByPID.size();
        
        Process[] processToMonitor = new Process[objects];

        for (String process:printReportGetProcessListByString()) {
            //find process description
//            String searchForProcessDescription = "//process[search_string='" + process + "']/description/text()";
            String processDescription = xf.lookupConfigFile("processDescription", process);

            //find process owner
//            String searchForProcessOwner = "//process[search_string='" + process + "']/owner/text()";
            String processOwner = xf.lookupConfigFile("processOwner", process);

            //find process group
//            String searchForProcessGroup = "//process[search_string='" + process + "']/@group";
            String processGroup = xf.lookupConfigFile("processGroup", process);
            
            //create Process object
            processToMonitor[objectCount] = new Process(processDescription, processOwner, processGroup, null, null, process, false, false);
            
            // increment var for next object
            objectCount++;
        }

        for (String process:printReportGetProcessListByPID()) {
            //set description
//            String searchForProcessDescription = "//process[pid_filename='" + process + "']/description/text()";
            String processDescription = xf.lookupConfigFile("processPIDdesc", process);
            
            //find process owner
//            String searchForProcessOwner = "//process[pid_filename='" + process + "']/owner/text()";
            String processOwner = xf.lookupConfigFile("processPIDowner", process);
            
            //find process group
//            String searchForProcessGroup = "//process[pid_filename='" + process + "']/@group";
            String processGroup = xf.lookupConfigFile("processPIDgroup", process);
            
            //create Process object
            processToMonitor[objectCount] = new Process(processDescription, processOwner, processGroup, null, process, null, false, true);
            
            // increment var for next object
            objectCount++;
        }

        // take the processes that need to be monitored and look for them in a list of 
        // running processes

        // get the command to list running processes from the config file
//        String searchForPSCommand = "//ps_command/text()";
        String psCommand = xf.lookupConfigFile("pscommand", null);
        
        // get a list of currently running processes
        Process p = new Process();
        List<String> AllRunningProcs = p.getRunningProcs(psCommand);
        
        // check whether the processes that need to be monitored are running
        Process[] foundRunningProcs = p.searchForProcess(processToMonitor, AllRunningProcs);
        
        List<Process> sortedByGroup = new ArrayList<>();
        for (Process process:foundRunningProcs) {
            sortedByGroup.add(process);
        }
        
        // sort processes by group so they can be displayed together
        Collections.sort(sortedByGroup);
        
        //get the process details we want to display
        ReportLine[] reportlines = new ReportLine[sortedByGroup.size()];
        final int PID = 0;
        final int OWNER = 1;
        final int RUNNINGTIME = 2;
        
        for (Process process:sortedByGroup) {
            if (process.getIsRunning()) {
                String[] processDetails = process.getProcessDetails().split("\\s+");
                reportlines[sortedByGroup.indexOf(process)] = new ReportLine(process.getGroup(), process.getDescription(), process.getIsRunning(), processDetails[OWNER], process.getOwner(), processDetails[PID], processDetails[RUNNINGTIME]);
            } else {
                reportlines[sortedByGroup.indexOf(process)] = new ReportLine(process.getGroup(), process.getDescription(), process.getIsRunning(), process.getOwner(), null, null, null);
            }
        }
        
        // create a report object to store output of process details
        ReportHeader rh = new ReportHeader(printReportGetHeaderDetails(), s.getHostname(), s.searchForServerDescription(xf));
        reportHeader = rh.headerText();
        //Report report = new Report(rh.headerText(), configFile, reportlines);
        
        // look up the header separator char
//        String reportComponent = "//separator_char/header/text()";
        String headerSeparatorChar = xf.lookupConfigFile("separatorHeader", null);
        
        // look up the report separator char
//        reportComponent = "//separator_char/report/text()";
        String reportSeparatorChar = xf.lookupConfigFile("separatorReport", null);
        
        //get colours and other vars used to generate the report
        //header colour
//        reportComponent = "//colours/header/text()";
        String headerColour = xf.lookupConfigFile("coloursHeader", null);
        
        //process not running colour
//        reportComponent = "//colours/not_running/text()";
        String notRunningColour = xf.lookupConfigFile("coloursNotRunning", null);
        
        //process running colour
//        reportComponent = "//colours/running/text()";
        String runningColour = xf.lookupConfigFile("coloursRunning", null);
        
        //process warning colour
//        reportComponent = "//colours/warning/text()";
        String warningColour = xf.lookupConfigFile("coloursWarning", null);

        //escape sequence
//        reportComponent = "//escseq/text()";
        String escapeSeq = xf.lookupConfigFile("escseq", null);
        
        //sequence terminator
//        reportComponent = "//seqterm/text()";
        String seqTerm = xf.lookupConfigFile("seqterm", null);
        
        //calculate length and print separator row
        ReportLineSeparator rls = new ReportLineSeparator(reportSeparatorChar);
        ReportLineSeparator rlshf = new ReportLineSeparator(headerSeparatorChar);
        //StringLength sl = new StringLength(0);
        highestCharCount = 0;
        if (reportHeader.length() > printReportGetHighestCharCount()) {
            printReportSetHighestCharCount(reportHeader);
        }

        String lineGroup = null;
        String nextLineGroup = null;
        String reportLineOutput = "";

        // find the maximum length of the separator line
        //for (ReportLine line : reportLines) {
        for (ReportLine line:reportlines) {
            if (line.getIsRunning()) {
                reportLineOutput = line.getDescription() + " is running. (Owner = " + line.getOwner() + " | Process = " + line.getPID() + " | Uptime = " + line.getRunningTime() + ")";
                if (reportLineOutput.length() > printReportGetHighestCharCount()) {
                    printReportSetHighestCharCount(reportLineOutput);
                }
            } else {
                reportLineOutput = line.getDescription() + " is not running under owner " + line.getOwner() + ".";
                if (reportLineOutput.length() > printReportGetHighestCharCount()) {
                    printReportSetHighestCharCount(reportLineOutput);
                }
            }
        }
        
        //print header
        System.out.println(unescape(escapeSeq) + headerColour + rlshf.reportLineSeparator(printReportGetHighestCharCount()) + unescape(escapeSeq) + seqTerm);
        System.out.println(unescape(escapeSeq) + headerColour + reportHeader + unescape(escapeSeq) + seqTerm);
        System.out.println(unescape(escapeSeq) + headerColour + rlshf.reportLineSeparator(printReportGetHighestCharCount()) + unescape(escapeSeq) + seqTerm);
        
        //print the rest of the report inserting a separator row after each change of group
        //for (ReportLine line : reportLines) {
            //int lineIndex = java.util.Arrays.asList(reportLines).indexOf(line);
        for (ReportLine line:reportlines) {
            int lineIndex = java.util.Arrays.asList(reportlines).indexOf(line);
            lineGroup = line.getGroup();
            //if (lineIndex + 1 < reportLines.length) { // "lineIndex + 1" is the next element in the array
                //nextLineGroup = java.util.Arrays.asList(reportLines).get(lineIndex + 1).getGroup();
            if (lineIndex + 1 < reportlines.length) { // "lineIndex + 1" is the next element in the array
                nextLineGroup = java.util.Arrays.asList(reportlines).get(lineIndex + 1).getGroup();
            }
            if (line.getIsRunning()) {
                reportLineOutput = line.getDescription() + " is running. (Owner = " + line.getOwner() + " | Process = " + line.getPID() + " | Uptime = " + line.getRunningTime() + ")";
                if (lineGroup.equals(nextLineGroup)) {
                    System.out.println(unescape(escapeSeq) + runningColour + reportLineOutput + unescape(escapeSeq) + seqTerm);
                    if (!line.getOwner().equals(line.getConfigOwner())) {
                        System.out.println(unescape(escapeSeq) + warningColour + "Expected owner of " + line.getDescription() + " is: " + line.getConfigOwner() + unescape(escapeSeq) + seqTerm);
                    }
                } else {
                    System.out.println(unescape(escapeSeq) + runningColour + reportLineOutput + unescape(escapeSeq) + seqTerm);
                    if (!line.getOwner().equals(line.getConfigOwner())) {
                        System.out.println(unescape(escapeSeq) + warningColour + "Expected owner of " + line.getDescription() + " is: " + line.getConfigOwner() + unescape(escapeSeq) + seqTerm);
                    }
                    System.out.println(rls.reportLineSeparator(printReportGetHighestCharCount()));
                }
            } else {
                reportLineOutput = line.getDescription() + " is not running under owner " + line.getOwner() + ".";
                if (lineGroup.equals(nextLineGroup)) {
                    System.out.println(unescape(escapeSeq) + notRunningColour + reportLineOutput + unescape(escapeSeq) + seqTerm);
                } else {
                    System.out.println(unescape(escapeSeq) + notRunningColour + reportLineOutput + unescape(escapeSeq) + seqTerm);
                    System.out.println(rls.reportLineSeparator(printReportGetHighestCharCount()));
                }
            }
        }
        System.out.println(unescape(escapeSeq) + headerColour + rlshf.reportLineSeparator(printReportGetHighestCharCount()) + unescape(escapeSeq) + seqTerm);
    }
}
