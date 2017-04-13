/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procmon;

import java.io.*;
import nu.xom.*;

/**
 *
 * @author john
 */
public class PropertyPrinterTest {
    public static void main(String[] args) throws Exception {
     
      Builder builder = new Builder();
      for (int i = 0; i < args.length; i++) {
          PropertyPrinter p = new PropertyPrinter();
          File f = new File(args[i]);
          Document doc = builder.build(f);
          p.writeNode(doc);
      }   
        
    }
}
