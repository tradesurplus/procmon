/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procmon;

import java.io.File;

/**
 *
 * @author john
 */
public class TestReadFile {
    public static void main(String[] args) {
        
        //check for user-defined XML file
        String configFile;
        if (args.length > 0) {
            configFile = args[0];
        } else {
            configFile = "procMonConfig.xml";
        }
        
        File f = new File(configFile);
//        String filecontents = ReadXMLFile.getContents(f);
//        System.out.println(filecontents);
    }
}
