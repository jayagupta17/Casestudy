package com.jaya.Casestudy.Service;

import com.jaya.Casestudy.DAO.ProductDAO;
import com.jaya.Casestudy.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductDAO productDAO;

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    @Transactional(readOnly = true)
    public Product getProductById(int prod_id) {
        Optional<Product> op = productDAO.findById(prod_id);
        if(op.isPresent())
            return op.get();
        return null;
    }

    @Transactional(readOnly = true)
    public List<Product> searchProducts(String str) {
        return productDAO.searchProducts(str);
    }

    @Transactional
    public boolean addOrModifyProduct(Product product) {
        Product p = productDAO.save(product);
        return p != null;
    }

    @Transactional
    public boolean deleteProductById(int prod_id) {
        long count = productDAO.count();
        productDAO.deleteById(prod_id);
        return count>productDAO.count();
    }
}
