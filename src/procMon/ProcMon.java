/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procMon;

import org.apache.commons.configuration2.ex.ConfigurationException;

/**
 *
 * @author john
 */
public class ProcMon {
    public static void main(String[] args) throws ConfigurationException {
        // get the name of the config file
        XMLConfigFile xf;
        if (args.length > 0) {
            xf = new XMLConfigFile(args[0]);
        } else {
            xf = new XMLConfigFile();
        }
        
        // ensure this server has been defined in the config file
        Server s = new Server();
        s.searchForServerConfig(xf);
        
        // generate ProcMon output
        Report r = new Report(xf, s);
        r.printReport();

        //method tests
//        s.setHostname("Dell-Opti755");
//        System.out.println("Server name is: " + s.getHostname());
//        System.out.println("Server description is: " + s.searchForServerDescription(xf));
//        System.out.println("Server type is: " + s.searchForServerType(xf));
//        System.out.println("separatorHeader: " + xf.lookupConfigFile("separatorHeader", null));
        
    }
}
