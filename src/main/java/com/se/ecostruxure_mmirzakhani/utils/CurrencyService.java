package com.se.ecostruxure_mmirzakhani.utils;

import com.se.ecostruxure_mmirzakhani.be.Currency;
import com.se.ecostruxure_mmirzakhani.be.CurrencySign;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class CurrencyService {
    private static final    File                            file            = new File("src/main/resources/static/currency_rates/eur.xml");
    private static final    Properties                      properties      = loadConfigFile();
    private static          DocumentBuilderFactory          dbf             = DocumentBuilderFactory.newInstance();

    /**
     * Currency converter from local to system currency
     * Supported currencies: /src/static/currency_rates/
     */
    public static double convert(Currency from, Currency to, double amount) {
        double convertedValue = 0;

        // both provided currencies are same like 'from: EUR' and 'to: EUR'
        if (from == to){
            return amount;
        }

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            document.getDocumentElement().normalize();

            // All the nodes
            NodeList nodeList = document.getElementsByTagName("item");

            // Iterate over nodes
            for (int n = 0; n < nodeList.getLength(); n++) {
                Node node = nodeList.item(n);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String baseCurrency = element.getElementsByTagName("baseCurrency").item(0).getTextContent();
                    String targetCurrency = element.getElementsByTagName("targetCurrency").item(0).getTextContent();
                    double exchangeRate = Double.parseDouble(element.getElementsByTagName("inverseRate").item(0).getTextContent());
                    if (targetCurrency.equals(from.name()) && baseCurrency.equals(to.name())) {
                        convertedValue = amount * exchangeRate;
                        break;
                    }
                }
            }

        } catch (RuntimeException | ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
        }

        return convertedValue;
    }

    /**
     * Format money amount in 1,234,567.89 and returns as string
     */
    public static String stringFormatter(double amount){
        DecimalFormat formatter = new DecimalFormat("#,##0.00");
        return formatter.format(amount);
    }

    /**
     * Format money amount in 123.34 and returns as double
     */
    public static double doubleFormatter(double amount){
        DecimalFormat formatter = new DecimalFormat("##0.00");
        return Double.parseDouble(formatter.format(amount));
    }

    /**
     * Load config file containing system currency
     */
    private static Properties loadConfigFile() {
        Properties properties = new Properties();
        try (InputStream inputStream = CurrencyService.class.getClassLoader().getResourceAsStream("db/system_currency.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
        return properties;
    }

    /**
     * Get system currently using currency
     */
    public static Currency getSystemCurrency(){
        return Currency.valueOf(properties.getProperty("currency"));
    }

}
