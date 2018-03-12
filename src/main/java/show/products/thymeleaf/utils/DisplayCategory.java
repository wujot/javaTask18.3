package show.products.thymeleaf.utils;

import show.products.thymeleaf.model.Product;

import java.util.ArrayList;
import java.util.List;

public class DisplayCategory {

    // method gets list of appropriate category
    public static List<Product> displayProductByCategory(List<Product> products, String category) {
        List<Product> productsByCategory = new ArrayList<>();
        for (Product product: products) {
            if (product.getCategory().equals(category))
                productsByCategory.add(product);
        }
        return productsByCategory;
    }

    // method calculate sum of products prices
    public static double sumOfPrices(List<Product> products) {
        double price = 0;
        double sum = 0;
        for (Product product : products) {
            price = Double.parseDouble(product.getPrice());
            sum += price;
        }
        return sum;
    }
}
