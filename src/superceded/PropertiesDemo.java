/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superceded;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
/**
 *
 * @author john
 */
public class PropertiesDemo {
    public static void main(String[] args) {
   Properties prop = new Properties();

   // add some properties
   prop.put("Height", "200");
   prop.put("Width", "15");

   try {

   // create a output and input as a xml file
   FileOutputStream fos = new FileOutputStream("properties.xml");
   FileInputStream fis = new FileInputStream("properties.xml");

   // store the properties in the specific xml
   prop.storeToXML(fos, null);

   // load from the xml that we saved earlier
   prop.loadFromXML(fis);

   // print the properties list
   prop.list(System.out);

   } catch (IOException ex) {
   ex.printStackTrace();
   }

   }
}
