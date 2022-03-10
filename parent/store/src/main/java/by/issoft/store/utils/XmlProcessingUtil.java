package by.issoft.store.utils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class XmlProcessingUtil {
    public static HashMap<String,String> xmlConfigMap = new HashMap<>();

    public static HashMap<String ,String> getXmlConfig() throws ParserConfigurationException, SAXException, IOException {
    //public static HashMap<String ,String> getXmlConfig(String path) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File("parent/store/src/main/resources/sortConfigFile.xml"), handler);
       // parser.parse(new File(path), handler);
        //for (Map.Entry entry:xmlConfigMap.entrySet()) System.out.println(entry);
        return xmlConfigMap;

    }

    private static class XMLHandler extends DefaultHandler {
        private  String currentNodeName;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            currentNodeName = qName;
        }

        //TODO Refactor
        @Override
        public void characters(char[] ch, int start, int length) {
            String data = new String(ch, start, length).replace("\n", "").trim();
            if (!data.isEmpty()) {
                if (currentNodeName.equals("name"))
                    xmlConfigMap.put("name", data);
                if (currentNodeName.equals("price"))
                    xmlConfigMap.put("price",data);
                if (currentNodeName.equals("rate"))
                    xmlConfigMap.put("rate", data);
            }
        }


    }
}

