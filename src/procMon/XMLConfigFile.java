/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procMon;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.tree.xpath.XPathExpressionEngine;

/**
 *
 * @author john
 */
public class XMLConfigFile {
    private final String filename;
    
    public XMLConfigFile() throws ConfigurationException {
        // if the user doesn't provide a config file then the default
        // is assumed to be "procMonConfig.xml"
        this.filename = "procMonConfig.xml";
//        testXMLConfigQuery(filename);
    }
    
    public XMLConfigFile(String filename) throws ConfigurationException {
        this.filename = filename;
//        testXMLConfigQuery(filename);
    }
    
    public String lookupConfigFile(String element, String elementVar) throws ConfigurationException {
        Configurations configs = new Configurations();
        File xmlFile = new File(filename);
        XMLConfiguration config = configs.xml(xmlFile);
        config.setExpressionEngine(new XPathExpressionEngine());
        String elementLookup = "";
        switch (element) {
            case "coloursHeader":
                elementLookup = "//colours/header";
                break;
            case "coloursNotRunning":
                elementLookup = "//colours/not_running";
                break;
            case "coloursRunning":
                elementLookup = "//colours/running";
                break;
            case "coloursWarning":
                elementLookup = "//colours/warning";
                break;
            case "description":
                elementLookup = "//server[hostname='" + elementVar + "']/description";
                break;
            case "escseq":
                elementLookup = "//escseq";
                break;
            case "header":
                elementLookup = "//header[name='" + elementVar + "']/header_detail";
                break;
//            case "processesByString":
//                elementLookup = "//process[@type='" + elementVar + "']/search_string";
//                break;
            case "processDescription":
                elementLookup = "//process[search_string='" + elementVar + "']/description";
                break;
            case "processGroup":
                elementLookup = "//process[search_string='" + elementVar + "']/@group";
                break;
            case "processOwner":
                elementLookup = "//process[search_string='" + elementVar + "']/owner";
                break;
            case "processPIDdesc":
                elementLookup = "//process[pid_filename='" + elementVar + "']/description";
                break;
            case "processPIDgroup":
                elementLookup = "//process[pid_filename='" + elementVar + "']/@group";
                break;
            case "processPIDowner":
                elementLookup = "//process[pid_filename='" + elementVar + "']/owner";
                break;
            case "pscommand":
                elementLookup = "//ps_command";
                break;
            case "separatorHeader":
                elementLookup = "//separator_char/header";
                break;
            case "separatorReport":
                elementLookup = "//separator_char/report";
                break;
            case "seqterm":
                elementLookup = "//seqterm";
                break;
            case "type":
                elementLookup = "//server[hostname='" + elementVar + "']/@type";
                break;
        }
        String value = config.getString(elementLookup);
        return value;
    }
    
    public List lookupProcessDetails(String searchType, String elementVar) throws ConfigurationException {
        List processes = new ArrayList<>();
        String elementLookup;
        Configurations configs = new Configurations();
        File xmlFile = new File(filename);
        XMLConfiguration config = configs.xml(xmlFile);
        config.setExpressionEngine(new XPathExpressionEngine());
        switch (searchType) {
            case "byString":
                elementLookup = "//process[@type='" + elementVar + "']/search_string";
                processes = config.getList(elementLookup);
                break;
            case "byPID":
                elementLookup = "//process[@type='" + elementVar + "']/pid_filename";
                processes = config.getList(elementLookup);
                break;
        }
        return processes;
    }

//    public String getServerDescription(String serverName) throws ConfigurationException {
//        Configurations configs = new Configurations();
//        File xmlFile = new File(filename);
//        XMLConfiguration config = configs.xml(xmlFile);
//        config.setExpressionEngine(new XPathExpressionEngine());
//        String foundDescription = config.getString("//server[hostname='" + serverName + "']/description");
//        return foundDescription;
//    }
//
//    public String getServerType(String serverName) throws ConfigurationException {
//        Configurations configs = new Configurations();
//        File xmlFile = new File(filename);
//        XMLConfiguration config = configs.xml(xmlFile);
//        config.setExpressionEngine(new XPathExpressionEngine());
//        String foundType = config.getString("//server[hostname='" + serverName + "']/@type");
//        return foundType;
//    }
    
    public Boolean queryServerConfig(String servername) throws ConfigurationException {
        Configurations configs = new Configurations();
        File xmlFile = new File(filename);
        XMLConfiguration config = configs.xml(xmlFile);
        boolean foundServer = false;
        List serverList = config.getList("servers.server.hostname");
        for (Object server : serverList) {
            if (server.toString().equals(servername)) {
                foundServer = true;
            }
        }
        if (!foundServer) {
            System.out.println("This host (" + servername + ") has not been defined in the config file.\nUpdate the file with the processes that need to be monitored on this server and try again.");
            System.exit(0);
        }
        return foundServer;
    }
    
//    public String query() {
//        // This needs to be coded.  See ConfigFile.java for functionality.
//        String queryResult = null;
//        return queryResult;
//    }
//    
//    public List<String> queryProcesses() {
//        // This needs to be coded.  See ConfigFile.java for functionality.
//        List<String> queryResult = new ArrayList<>();
//        return queryResult;
//    }
//    
//    public Boolean queryServerConfig(String serverSearch) throws ConfigurationException {
//        // This needs to be coded.  See ConfigFile.java for functionality.
//        Configurations configs = new Configurations();
//        XMLConfiguration config = configs.xml(filename);
//        config.setExpressionEngine(new XPathExpressionEngine());
//        config.getString(serverSearch);
//        
//        Boolean check = false;
//        return check;
//    }
    
//    private void testXMLConfigQuery(String conf) throws ConfigurationException {
//        Configurations configs = new Configurations();
//        File xmlFile = new File(conf);
//        XMLConfiguration config = configs.xml(xmlFile);
//        String name = "z210";
//        config.setExpressionEngine(new XPathExpressionEngine());
//// ######## search for server name in conf file
////        boolean foundServer = config.getBoolean("count(//server[hostname='" + name + "'])>0", false);
//        String foundServer;
//        JXPathContext context = JXPathContext.newContext(config);
//        CompiledExpression expr = context.compile("count(//server[hostname='" + name + "'])>0");
//        foundServer = expr.getValue(context).toString();
//        System.out.println(foundServer);
////        List serverList = config.getList("servers.server.hostname");
////        for (Object server : serverList) {
////            if (server.toString().equals(name)) {
////                System.out.println("Config exists for server: " + server.toString());
////                foundServer = true;
////            }
////        }
////        if (!foundServer) {
////            System.out.println("This host (" + name + ") has not been defined in the config file.\nUpdate the file with the processes that need to be monitored on this server and try again.");
////            System.exit(0);
////        }
//// ########
//// ######## search for server type
//        config.setExpressionEngine(new XPathExpressionEngine());
//        String foundType = config.getString("//server[hostname='" + name + "']/@type");
//        System.out.println("Server type is: " + foundType);
//        
//        // ######## search for server Description
//        config.setExpressionEngine(new XPathExpressionEngine());
//        String foundDescription = config.getString("//server[hostname='" + name + "']/description");
//        System.out.println("Server description is: " + foundDescription);
//    }
}
