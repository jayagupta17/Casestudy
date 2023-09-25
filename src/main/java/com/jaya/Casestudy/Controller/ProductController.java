package com.jaya.Casestudy.Controller;

import com.jaya.Casestudy.Entity.Product;
import com.jaya.Casestudy.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping(value = "/allProducts")
    public String getAllProducts(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "displayProducts";
    }

    @PostMapping(value = "/search")
    public String searchProducts(@RequestParam("str") String str, Model model) {
        model.addAttribute("products", productService.searchProducts(str));
        return "displayProducts";
    }
}
