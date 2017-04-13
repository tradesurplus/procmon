/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procmon;

import java.io.IOException;
import java.util.Iterator;
import nu.xom.*;

/**
 *
 * @author john
 */
public class TestProcessList {
    public static void main(String[] args) {
        ProcessList pl = new ProcessList("ps -eo pid,ruser,stime,args");
        Iterator it = pl.getRunningProcs().iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
        try {
            Builder parser = new Builder();
            Document doc = parser.build("file:///home/john/bin/NetBeansProjects/procMon_OO/procMonConfig.xml");
            Element root = doc.getRootElement();
            listChildren(root, 0);
        }
        catch (ParsingException ex) {
            System.err.println("Document is malformed.");
        }
        catch (IOException ex) {
            System.err.println("Could not read document.");
        }
    }
    
    public static void listChildren(Element current, int depth) {
        
        System.out.println(current.getQualifiedName());
        Elements children = current.getChildElements();
        for (int i = 0; i < children.size(); i++) {
            listChildren(children.get(i), depth+1);
        }
        String data = "";
        if (current instanceof Element) {
            Element temp = (Element) current;
            data = ": " + temp.getQualifiedName();   
            for (int i = 0; i < temp.getAttributeCount(); i++) {
                Attribute attribute = temp.getAttribute(i);
                String attValue = attribute.getValue();
                String value = current.getValue();
                attValue = attValue.replace('\n', ' ').trim();
                if (value.length() >= 20) {
                    attValue = attValue.substring(0, 17) + "..."; 
                }
                data += "\r\n    ";
                data += attribute.getQualifiedName();
                data += "=";
                data += attValue;
            }
        }
    }
}
