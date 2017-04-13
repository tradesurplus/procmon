package procmon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author John Carruthers
 */
public class Server {
    private String description;
    private String hostname;
    private String type;
        
    public Server(String description, String hostname, String type) {
        this.description = description;
        this.hostname = hostname;
        this.type = type;
    }
    
    public Server() {
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    
    public String getHostname() {
        String hostname = null;
        String str;
        try {
            java.lang.Process proc = Runtime.getRuntime().exec("hostname");
            BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            while ((str = br.readLine()) != null) {
                hostname = str;
            }
        } catch (IOException ioe) {
                System.err.println(ioe);
                System.exit(1);
        }
        return hostname;
    }

    public String getType() {
        return type;
    }
    
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Server details - ");
//        sb.append("Description: ").append(getDescription());
//        sb.append("\t\t");
//        sb.append("Type: ").append(getType());
//        sb.append("\t");
//        sb.append("Hostname: ").append(getHostname());
//        return sb.toString();
//    }
}
