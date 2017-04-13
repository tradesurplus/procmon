package procmon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author John Carruthers
 */
public class ReportHeader {
    private String name;
    private String headerDetail;
    private String hostname;
    private String separatorChar;
    
//    public ReportHeader(String name, String headerDetail, String separatorChar) {
//        this.name = name;
//        this.headerDetail = headerDetail;
//        this.separatorChar = separatorChar;
//    }
    public ReportHeader() {
    }
    
    public String getHeaderDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        String headerDate = sdf.format(date);
        return headerDate;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    
    public void setHeaderDetail(String headerDetail) {
        this.headerDetail = headerDetail;
    }
    public String getHeaderDetail() {
        return headerDetail;
    }
    
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
    public String getHostname() {
        return hostname;
    }
    
    public void setSeparatorChar(String separatorChar) {
        this.separatorChar = separatorChar;
    }
    public String getSeparatorChar() {
        return separatorChar;
    }
    
    public String getReportLineSeparator(int separatorlength) {
        StringBuilder underline = new StringBuilder(separatorlength);
        for (int i = 0; i < separatorlength; i++){
            underline.append(getSeparatorChar());
        }
        return underline.toString();
    }
    
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Header details:");
//        sb.append("\n ");
//        sb.append("Header Date -> ").append(getHeaderDate());
//        sb.append("\n ");
//        sb.append("Name -> ").append(getName());
//        sb.append("\n ");
//        sb.append("Header Detail -> ").append(getHeaderDetail());
//        sb.append("\n ");
//        sb.append("Hostname -> ").append(getHostname());
//        sb.append("\n ");
//        sb.append("Separator Char -> ").append(getSeparatorChar());
//    return sb.toString();
//    }
}
