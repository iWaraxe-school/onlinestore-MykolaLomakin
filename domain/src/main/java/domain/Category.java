package domain;

import products.Product;
import store.Comparator.ProductComparator;

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
         System.out.println(categoryName + ":");
         for (Product product : productList) {
             product.setProduct();
         }
     }
     public List<Product> getProductList() {
         return ProductComparator.sortProductList(productList, "price");
     }

     public void sort() {
         StringBuilder info = new StringBuilder();
         info.append(String.format("%s category:%n", getCategoryName()));
         List<Product> plist = ProductComparator.sortProductList(productList, "");
         for (Product product : plist) {
             info.append(product);
         }
         info.append(String.format("Number of products: %s%n", getProductSize()));
         System.out.println(info);
     }
}
