package domain;

import products.Product;
import store.Comparator.MultiFieldComparator;

import java.util.ArrayList;
import java.util.List;
 public class Category {
        private final String categoryName;
        private List<Product> productList;

        public Category(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void addProduct(Product product) {
            if (productList == null) {
                productList = new ArrayList<>();
            }
            productList.add(product);
        }

        public int getProductSize(){
            return productList.size();
        }

     public void printCategory() {
         StringBuilder sb = new StringBuilder();
         sb.append(categoryName).append(":\n");

         for (Product product : productList) {
             sb.append("Product Name: ").append(product.getName()).append("\n");
             sb.append("Product Price: ").append(product.getPrice()).append("\n\n");
         }
         System.out.println(sb);
     }
     public List<Product> getProductList() {
         String defaultSortKey = System.getProperty("defaultSortKey", "price");
         return MultiFieldComparator.sortProductList(productList, defaultSortKey);
     }

     public void sort() {
         StringBuilder info = new StringBuilder();
         info.append(String.format("%s category:%n", getCategoryName()));
         List<Product> plist = MultiFieldComparator.sortProductList(productList, "");
         for (Product product : plist) {
             info.append(product);
         }
         info.append(String.format("Number of products: %s%n", getProductSize()));
         System.out.println(info);
     }
}
