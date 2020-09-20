/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 *
 * @author john
 */
public class XmlDoc {
    public static Document document(String xmlString) {
        Document doc = null;
        try{
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
 
        InputStream inputStream = new    ByteArrayInputStream(xmlString.getBytes());
        doc =  dBuilder.parse(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
        return doc;
    }
}
