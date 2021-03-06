package com.vadim.store.utility;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class XmlReader implements ConditionsReader {

    @Override
    public Map<String, String> getSortConditions(String path) {
        File xmlFile = new File(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Map<String, String> sortParameters = new LinkedHashMap<>();
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();
            Element docEl = document.getDocumentElement();
            Node childNode = docEl.getFirstChild();
            while (childNode.getNextSibling() != null) {
                childNode = childNode.getNextSibling();
                if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element childElement = (Element) childNode;
                    sortParameters.putAll(getMap(childElement));
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return sortParameters;
    }

    private Map<String, String> getMap(Node node) {
        Map<String, String> nodeWithValue = new HashMap<String, String>();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            if (!node.getTextContent().trim().isEmpty()) {
                nodeWithValue.put(node.getNodeName(), node.getTextContent());
            }
        }
        return nodeWithValue;
    }
}
