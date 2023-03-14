package store.Comparator;

import XMLparser.XMLParser;
import products.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ProductComparator implements Comparator<Product> {

    public int compare(Product o1, Product o2) {
        return o1.getName().compareTo(o2.getName());
    }

    private static Comparator<Product> getComparator(String sortKey) {
        return switch (sortKey) {
            case "name" -> Comparator.comparing(Product::getName);
            case "price" -> Comparator.comparing(Product::getPrice);
            case "rate" -> Comparator.comparing(Product::getRate);
            default -> throw new IllegalArgumentException();
        };
    }

    public static List<Product> sortProductList(List<Product> productList, String sortKey) {

        List<Product> plist = new ArrayList(productList);
        Map<String, String> sortingMap = XMLParser.fieldSortOrderMap();
        if (sortKey.isBlank()) { sortKey = "price"; }

        for (Map.Entry<String, String> me : sortingMap.entrySet()) {
            if (me.getKey().equals(sortKey)) {
                if (me.getValue().equals(SortingTypes.ASC.name().toLowerCase())) {
                    plist.sort(getComparator(me.getKey()));
                } else if (me.getValue().equals(SortingTypes.DESC.name().toLowerCase())) {
                    plist.sort(getComparator(me.getKey()).reversed());
                }
            }
        }
        return plist;
    }

    public static List<Product> sortProductReversed(List<Product> productList, String sortKey) {
        switch (sortKey) {
            case  ("name"):
                productList.sort(getComparator("name").reversed());
                break;
            case ("price"):
                productList.sort(getComparator("price").reversed());
                break;
            case ("rate"):
                productList.sort(getComparator("rate").reversed());
                break;
            default:
                break;
        }
        return productList;
    }
}
