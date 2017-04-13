/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superceded;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


public class QueryXMLfile1 {
  public void query(String configFile, String dataQuery) throws ParserConfigurationException, SAXException,
      IOException, XPathExpressionException {
    // standard for reading an XML file
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder;
    Document doc = null;
    XPathExpression expr = null;
    builder = factory.newDocumentBuilder();
    doc = builder.parse(configFile);

    // create an XPathFactory
    XPathFactory xFactory = XPathFactory.newInstance();

    // create an XPath object
    XPath xpath = xFactory.newXPath();

    if (dataQuery.equals("server")) {
        // compile the XPath expression
        expr = xpath.compile("//server[@type='pc']/hostname/text()");
        // run the query and get a nodeset
        Object result = expr.evaluate(doc, XPathConstants.NODESET);

        // cast the result to a DOM NodeList
        NodeList nodes = (NodeList) result;
        for (int i=0; i<nodes.getLength();i++){
          System.out.println(nodes.item(i).getNodeValue());
        }
    } else if (dataQuery.equals("process")) {
        //do stuff;
    }
    // compile the XPath expression
    expr = xpath.compile("//person[firstname='Lars']/lastname/text()");
    // run the query and get a nodeset
    Object result = expr.evaluate(doc, XPathConstants.NODESET);

    // cast the result to a DOM NodeList
    NodeList nodes = (NodeList) result;
    for (int i=0; i<nodes.getLength();i++){
      System.out.println(nodes.item(i).getNodeValue());
    }

//    // new XPath expression to get the number of people with name Lars
//    expr = xpath.compile("count(//person[firstname='Lars'])");
//    // run the query and get the number of nodes
//    Double number = (Double) expr.evaluate(doc, XPathConstants.NUMBER);
//    System.out.println("Number of objects " +number);

//    // do we have more than 2 people with name Lars?
//    expr = xpath.compile("count(//person[firstname='Lars']) >2");
//    // run the query and get the number of nodes
//    Boolean check = (Boolean) expr.evaluate(doc, XPathConstants.BOOLEAN);
//    System.out.println(check);
  }

  public static void main(String[] args) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
    QueryXMLfile1 process = new QueryXMLfile1();
    process.query("procMonConfig.xml", "server");
  }
}
