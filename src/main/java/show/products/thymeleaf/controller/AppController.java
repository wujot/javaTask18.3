package show.products.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import show.products.thymeleaf.model.Product;
import show.products.thymeleaf.model.Products;
import show.products.thymeleaf.utils.DisplayCategory;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {

    private Products products;

    public AppController(Products products) {
        this.products = products;
    }

    // display homepage
    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("product", new Product());
        return "index";
    }

    // add product
    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute Product product, Model model) {
        model.addAttribute("product", product);
        products.addProduct(product);
        model.addAttribute("added", "Product was added");
        return "redirect:/";
    }

    // display products
    @GetMapping("/display")
    public String allProducts(@RequestParam(required = false) String category, Model model) {
        List<Product> listOfProducts;
        double sum = 0;
        if (category != null) {
            List<Product> productsByCategory = displayProductByCategory(products.getProducts(), category);
            listOfProducts = productsByCategory;
        } else {
            listOfProducts = products.getProducts();
            category = "All products";
        }
        sum = sumOfPrices(listOfProducts);
        model.addAttribute("listOfProduct", listOfProducts);
        model.addAttribute("sum", sum);
        model.addAttribute("category", category);
        return "display";
    }

    // method gets list of appropriate category
    private static List<Product> displayProductByCategory(List<Product> products, String category) {
        List<Product> productsByCategory = new ArrayList<>();
        for (Product product: products) {
            if (product.getCategory().equals(category))
                productsByCategory.add(product);
        }
        return productsByCategory;
    }

    // method calculate sum of products prices
    private static double sumOfPrices(List<Product> products) {
        double price = 0;
        double sum = 0;
        for (Product product : products) {
            price = product.getPrice();
            sum += price;
        }
        return sum;
    }
}
