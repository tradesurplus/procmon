/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procmon;

/**
 *
 * @author john
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    static public String getContents(File aFile) {
    StringBuilder contents = new StringBuilder();
    
    try {
      //use buffering, reading one line at a time
      //FileReader always assumes default encoding is OK!
      BufferedReader input =  new BufferedReader(new FileReader(aFile));
      try {
        String line; //not declared within while loop
        /*
        * readLine is a bit quirky :
        * it returns the content of a line MINUS the newline.
        * it returns null only for the END of the stream.
        * it returns an empty String if two newlines appear in a row.
        */
        while ((line = input.readLine()) != null){
          contents.append(line);
        }
      }
      finally {
        input.close();
      }
    }
    catch (IOException ioe){
        System.err.println(ioe);
        System.exit(1);
    }
    return contents.toString();
  }
}
