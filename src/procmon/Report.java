package procmon;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author John Carruthers
 */
public class Report {
    private String reportHeader;
    private String configFile;
    private ReportLine[] reportLines;
    
    public Report() {
    }
    
    public Report(String reportHeader, String configFile, ReportLine[] reportLines) {
        this.reportLines = reportLines;
        this.configFile = configFile;
        this.reportHeader = reportHeader;
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
    
    public void printReport() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        QueryXMLfile qxf = new QueryXMLfile();
        
        // look up the header separator char
        String reportComponent = "//separator_char/header/text()";
        String headerSeparatorChar = qxf.query(configFile, reportComponent);
        
        // look up the report separator char
        reportComponent = "//separator_char/report/text()";
        String reportSeparatorChar = qxf.query(configFile, reportComponent);
        
        //get colours and other vars used to generate the report
        //header colour
        reportComponent = "//colours/header/text()";
        String headerColour = qxf.query(configFile, reportComponent);
        
        //process not running colour
        reportComponent = "//colours/not_running/text()";
        String notRunningColour = qxf.query(configFile, reportComponent);
        
        //process running colour
        reportComponent = "//colours/running/text()";
        String runningColour = qxf.query(configFile, reportComponent);
        
        //process warning colour
        reportComponent = "//colours/warning/text()";
        String warningColour = qxf.query(configFile, reportComponent);

        //escape sequence
        reportComponent = "//escseq/text()";
        String escapeSeq = qxf.query(configFile, reportComponent);
        
        //sequence terminator
        reportComponent = "//seqterm/text()";
        String seqTerm = qxf.query(configFile, reportComponent);
        
        //calculate length and print separator row
        ReportLineSeparator rls = new ReportLineSeparator(reportSeparatorChar);
        ReportLineSeparator rlshf = new ReportLineSeparator(headerSeparatorChar);
        StringLength sl = new StringLength(0);
        if (reportHeader.length() > sl.getHighestCharCount()) {
            sl.setHighestCharCount(reportHeader);
        }

        String lineGroup = null;
        String nextLineGroup = null;
        String reportLineOutput = "";

        // find the maximum length of the separator line
        for (ReportLine line : reportLines) {
            if (line.getIsRunning()) {
                reportLineOutput = line.getDescription() + " is running. (Owner = " + line.getOwner() + " | Process = " + line.getPID() + " | Uptime = " + line.getRunningTime() + ")";
                if (reportLineOutput.length() > sl.getHighestCharCount()) {
                    sl.setHighestCharCount(reportLineOutput);
                }
            } else {
                reportLineOutput = line.getDescription() + " is not running under owner " + line.getOwner() + ".";
                if (reportLineOutput.length() > sl.getHighestCharCount()) {
                    sl.setHighestCharCount(reportLineOutput);
                }
            }
        }
        
        //print header
        System.out.println(unescape(escapeSeq) + headerColour + rlshf.reportLineSeparator(sl.getHighestCharCount()) + unescape(escapeSeq) + seqTerm);
        System.out.println(unescape(escapeSeq) + headerColour + reportHeader + unescape(escapeSeq) + seqTerm);
        System.out.println(unescape(escapeSeq) + headerColour + rlshf.reportLineSeparator(sl.getHighestCharCount()) + unescape(escapeSeq) + seqTerm);
        
        //print the rest of the report inserting a separator row after each change of group
        for (ReportLine line : reportLines) {
            int lineIndex = java.util.Arrays.asList(reportLines).indexOf(line);
            lineGroup = line.getGroup();
            if (lineIndex + 1 < reportLines.length) { // "lineIndex + 1" is the next element in the array
                nextLineGroup = java.util.Arrays.asList(reportLines).get(lineIndex + 1).getGroup();
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
                    System.out.println(rls.reportLineSeparator(sl.getHighestCharCount()));
                }
            } else {
                reportLineOutput = line.getDescription() + " is not running under owner " + line.getOwner() + ".";
                if (lineGroup.equals(nextLineGroup)) {
                    System.out.println(unescape(escapeSeq) + notRunningColour + reportLineOutput + unescape(escapeSeq) + seqTerm);
                } else {
                    System.out.println(unescape(escapeSeq) + notRunningColour + reportLineOutput + unescape(escapeSeq) + seqTerm);
                    System.out.println(rls.reportLineSeparator(sl.getHighestCharCount()));
                }
            }
        }
        System.out.println(unescape(escapeSeq) + headerColour + rlshf.reportLineSeparator(sl.getHighestCharCount()) + unescape(escapeSeq) + seqTerm);
    }
}
