package com.jaya.Casestudy.Controller;

import com.jaya.Casestudy.Entity.Cart;
import com.jaya.Casestudy.Entity.Order;
import com.jaya.Casestudy.Entity.Product;
import com.jaya.Casestudy.Service.CartService;
import com.jaya.Casestudy.Service.OrderService;
import com.jaya.Casestudy.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/mycart")
public class CartController {
    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @GetMapping(value = "allProducts")
    public ModelAndView getAllProducts(){
        double total = 0;
        ModelAndView modelAndView = new ModelAndView("/cartItems");
        List<Cart> products = cartService.getAllProducts();
        for(Cart p: products) {
            total += p.getProductPrice();
        }
        modelAndView.addObject("cartItems", products);
        modelAndView.addObject("total", total);
        return modelAndView;
    }

    @GetMapping(value = "/{product_id}")
    public ModelAndView addProduct(@PathVariable int product_id) {
        Product p = productService.getProductById(product_id);
        cartService.addOrModifyProduct(new Cart(p.getProductId(), p.getProductName(), p.getProductPrice()));
        return getAllProducts();
    }

    @GetMapping(value = "/remove/{product_id}")
    public ModelAndView deleteProductById(@PathVariable("product_id") int product_id) {
        cartService.deleteProductById(product_id);
        return getAllProducts();
    }

    @GetMapping(value = "/checkout")
    public ModelAndView checkout() {
        ModelAndView modelAndView = new ModelAndView("/checkoutPage");
        double total = 0;
        List<Cart> products = cartService.getAllProducts();
        for(Cart p: products) {
            total += p.getProductPrice();
        }
        modelAndView.addObject("total", total);
        modelAndView.addObject("cartItems", products);
        return modelAndView;
    }

    @PostMapping(value = "/orderDetails")
    public ModelAndView addOrder(@RequestParam("fname")String fname, @RequestParam("lname")String lname, @RequestParam("mobile")String mobile, @RequestParam("email")String email, @RequestParam("address")String address) {
        ModelAndView modelAndView = new ModelAndView("/confirmationPAGE");
        double total = 0;
        List<Cart> products = cartService.getAllProducts();
        for(Cart p: products) {
            total += p.getProductPrice();
        }
        orderService.addOrder(new Order(mobile, fname, lname, email, address, total));
        cartService.deleteAll();
        return modelAndView;
    }
}
