package procmon;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author John Carruthers
 */
public class ReportHeader {
    private String headerDetail;
    private String hostname;
    private String serverDescription;
    
    public ReportHeader(String headerDetail, String hostname, String serverDescription) {
        this.headerDetail = headerDetail;
        this.hostname = hostname;
        this.serverDescription = serverDescription;
    }
    
    public String formatHeaderDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        String headerDate = sdf.format(date);
        return headerDate;
    }
    
    public String headerText() {
        String headerText = String.format("%s        %s        %s (%s)", formatHeaderDate(), headerDetail, hostname, serverDescription);
        return headerText;
    }
}
