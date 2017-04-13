/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procmon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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


public class QueryXMLfile {
    public String query(String configFile, String dataQuery) throws ParserConfigurationException, SAXException,
      IOException, XPathExpressionException {
        // standard for reading an XML file
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;
        XPathExpression expr = null;
        builder = factory.newDocumentBuilder();
        doc = builder.parse(configFile);
        String queryResult = null;

        // create an XPathFactory
        XPathFactory xFactory = XPathFactory.newInstance();

        // create an XPath object
        XPath xpath = xFactory.newXPath();

        // compile the XPath expression
        //expr = xpath.compile("//person[firstname='Lars']/lastname/text()");
        //expr = xpath.compile("//server[hostname='vostro']/description/text()");
        expr = xpath.compile(dataQuery);
        // run the query and get a nodeset
        Object result = expr.evaluate(doc, XPathConstants.NODESET);

        // cast the result to a DOM NodeList
        NodeList nodes = (NodeList) result;
        for (int i=0; i<nodes.getLength();i++){
          //System.out.println(nodes.item(i).getNodeValue());
          queryResult = nodes.item(i).getNodeValue();
        }
        return queryResult;

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
    
    public List<String> queryProcesses(String configFile, String dataQuery) throws ParserConfigurationException, SAXException,
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

        // compile the XPath expression
        //expr = xpath.compile("//person[firstname='Lars']/lastname/text()");
        //expr = xpath.compile("//server[hostname='vostro']/description/text()");
        expr = xpath.compile(dataQuery);
        // run the query and get a nodeset
        Object result = expr.evaluate(doc, XPathConstants.NODESET);

        // cast the result to a DOM NodeList
        NodeList nodes = (NodeList) result;
        //List<String> queryResult = new String[nodes.getLength()];
        List<String> queryResult = new ArrayList<String>();
        for (int i=0; i<nodes.getLength();i++){
          //System.out.println(nodes.item(i).getNodeValue());
          //queryResult[i] = nodes.item(i).getNodeValue();
          queryResult.add(nodes.item(i).getNodeValue());
        }
        return queryResult;
    }
    
    public Boolean queryServerConfig(String configFile, String dataQuery) throws ParserConfigurationException, SAXException,
          IOException, XPathExpressionException {
        // read the XML file
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;
        XPathExpression expr = null;
        builder = factory.newDocumentBuilder();
        doc = builder.parse(configFile);

        XPathFactory xFactory = XPathFactory.newInstance();
        XPath xpath = xFactory.newXPath();
        expr = xpath.compile(dataQuery);
        Boolean check = (Boolean) expr.evaluate(doc, XPathConstants.BOOLEAN);
        return check;
    }
//  public static void main(String[] args) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
//    QueryXMLfile process = new QueryXMLfile();
//    process.query();
//  }
}
