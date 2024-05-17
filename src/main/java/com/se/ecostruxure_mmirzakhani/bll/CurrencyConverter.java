package com.se.ecostruxure_mmirzakhani.bll;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    private Map<String, Float> exchangeRates;

    public CurrencyConverter(String xmlFilePath) {
        exchangeRates = new HashMap<>();
        try {parseXml(xmlFilePath);} catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    private void parseXml(String xmlFilePath) throws IOException, SAXException {
        try {
            File file = new File(xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            Element root = doc.getDocumentElement();
            NodeList itemNodes = root.getElementsByTagName("item");
            for(int i = 0; i < itemNodes.getLength(); i++) {
                Node itemNode = itemNodes.item(i);
                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element itemElement = (Element) itemNode;
                    String targetCurrency = itemElement.getElementsByTagName("targetCurrency").item(0).getTextContent();
                    float exchangeRate = Float.parseFloat((itemElement.getElementsByTagName("exchangeRate").item(0).getTextContent()));
                    exchangeRates.put(targetCurrency, exchangeRate);

                }
            }} catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

    }

    public double convertToEuro(double amount, String originalCurrency) {
        if (exchangeRates.containsKey(originalCurrency)) {
            float exchangeRate = exchangeRates.get(originalCurrency);
            return amount / exchangeRate;
        } else {
            System.out.println("Exchange rate not found for currency: " + originalCurrency);
            return Double.NaN;
        }
    }

}

