package superceded;

/**
 *
 * @author John Carruthers
 */

//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXMLFile {

//  /**
//  * Fetch the entire contents of a text file, and return it in a String.
//  * This style of implementation does not throw Exceptions to the caller.
//  *
//  * @param aFile is a file which already exists and can be read.
//     * @return 
//  */
//  static public String getContents(File aFile) {
//    StringBuilder contents = new StringBuilder();
//    try {
//      BufferedReader input =  new BufferedReader(new FileReader(aFile));
//      try {
//        String line;
//        while ((line = input.readLine()) != null){
//          contents.append(line);
//        }
//      }
//      finally {
//        input.close();
//      }
//    }
//    catch (IOException ioe){
//        System.err.println(ioe);
//        System.exit(1);
//    }
//    return contents.toString();
//  }
    public static void main(String[] args) {

    try {

	File file = new File("procMonConfig.xml");

	DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

	Document doc = dBuilder.parse(file);

	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

	if (doc.hasChildNodes()) {

		printNote(doc.getChildNodes());

	}

    } catch (Exception e) {
	System.out.println(e.getMessage());
    }

  }

  private static void printNote(NodeList nodeList) {

    for (int count = 0; count < nodeList.getLength(); count++) {

	Node tempNode = nodeList.item(count);

	// make sure it's element node.
	if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

		// get node name and value
		System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
		System.out.println("Node Value =" + tempNode.getTextContent());

		if (tempNode.hasAttributes()) {

			// get attributes names and values
			NamedNodeMap nodeMap = tempNode.getAttributes();

			for (int i = 0; i < nodeMap.getLength(); i++) {

				Node node = nodeMap.item(i);
				System.out.println("attr name : " + node.getNodeName());
				System.out.println("attr value : " + node.getNodeValue());

			}

		}

		if (tempNode.hasChildNodes()) {

			// loop again if has child nodes
			printNote(tempNode.getChildNodes());

		}

		System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

	}

    }

  }
}