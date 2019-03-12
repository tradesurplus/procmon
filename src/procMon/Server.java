package procMon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.commons.configuration2.ex.ConfigurationException;

/**
 *
 * @author John Carruthers
 */
public class Server {
    private String description;
//    private String type;
    private String hostname;
        
    public Server(String description, String type) {
        this.description = description;
//        this.type = type;
    }
    
    public Server() {
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    
    public String queryHostnameFromOS() {
        String name = null;
        String str;
        try {
            java.lang.Process proc = Runtime.getRuntime().exec("hostname");
            BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            while ((str = br.readLine()) != null) {
                name = str;
            }
        } catch (IOException ioe) {
                System.err.println(ioe);
                System.exit(1);
        }
        return name;
    }
    
    public String getHostname() {
        return hostname;
    }
    
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

//    public String getType() {
//        return type;
//    }
//    
//    public void setType(String type) {
//        this.type = type;
//    }
    
    public void searchForServerConfig(XMLConfigFile xf) throws ConfigurationException {
        String name = queryHostnameFromOS();
        setHostname(name);
        xf.queryServerConfig(name);
    }
    
    public String searchForServerType(XMLConfigFile xf) throws ConfigurationException {
        // based on server's hostname, get server type from XML file
        String serverType = xf.lookupConfigFile("type", hostname);
        return serverType;
    }
    
    public String searchForServerDescription(XMLConfigFile xf) throws ConfigurationException {
        // based on server hostname, get server description
        String serverDescription = xf.lookupConfigFile("description", hostname);
        setDescription(serverDescription);
        return serverDescription;
    }
}
