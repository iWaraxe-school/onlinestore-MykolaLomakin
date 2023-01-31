package XMLparser;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class XMLParser {
    public static final String XMLPATH = "src/main/resources/config.xml";

    public Map<String, String> fieldSortOrderMap() {
        Map<String, String> fieldSortDirectionMap = new LinkedHashMap<>();

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new File(XMLPATH));
            doc.getDocumentElement().normalize();
            Node first = doc.getElementsByTagName("sort").item(0);
            NodeList nodeList = first.getChildNodes();
            Node current;

            for (int i = 0; i < nodeList.getLength(); i++) {
                current = nodeList.item(i);
                fieldSortDirectionMap.put(current.getNodeName(), current.getTextContent());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fieldSortDirectionMap;
    }
}
