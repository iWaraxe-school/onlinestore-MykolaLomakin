package store.Comparator;

import XMLparser.XMLParser;
import products.Product;

import java.util.*;

public class MultiFieldComparator implements Comparator<Product> {
    private final Map<String, Comparator<Product>> comparators;

    public MultiFieldComparator(Map<String, String> sortingMap) {
        comparators = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : sortingMap.entrySet()) {
            String fieldName = entry.getKey();
            String sortDirection = entry.getValue();
            Comparator<Product> comparator = getComparator(fieldName, sortDirection);
            comparators.put(fieldName, comparator);
        }
    }

    public MultiFieldComparator() {
        comparators = new LinkedHashMap<>();
    }

    @Override
    public int compare(Product o1, Product o2) {
        for (Comparator<Product> comparator : comparators.values()) {
            int result = comparator.compare(o1, o2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }


    private Comparator<Product> getComparator(String fieldName, String sortDirection) {
        Comparator<Product> comparator;
        switch (fieldName) {
            case "name":
                comparator = Comparator.comparing(Product::getName);
                break;
            case "price":
                comparator = Comparator.comparing(Product::getPrice);
                break;
            case "rate":
                comparator = Comparator.comparing(Product::getRate);
                break;
            default:
                throw new IllegalArgumentException("Unsupported field name");
        }
        if (sortDirection.equals("desc")) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    public static List<Product> sortProductList(List<Product> productList, String sortKey) {
        List<Product> plist = new ArrayList<>(productList);
        Map<String, String> sortingMap = XMLParser.fieldSortOrderMap("store1/src/main/resources/config.xml");
        if (sortKey.isBlank()) {
            sortKey = "price";
        }
        Comparator<Product> comparator = new MultiFieldComparator(sortingMap);
        plist.sort(comparator);
        return plist;
    }
}

