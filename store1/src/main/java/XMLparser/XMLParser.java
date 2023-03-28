package XMLparser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLParser {

    public static Map<String, String> fieldSortOrderMap(String filePath) {
        Map<String, String> fieldSortOrderMap = new HashMap<>();

        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("field");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String fieldName = element.getAttribute("name");
                    String sortOrder = element.getAttribute("sortOrder");
                    List<String> sortOrders = Arrays.asList(sortOrder.split(","));
                    String sortOrderString = String.join(",", sortOrders);
                    fieldSortOrderMap.put(fieldName, sortOrderString);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fieldSortOrderMap;
    }
}
