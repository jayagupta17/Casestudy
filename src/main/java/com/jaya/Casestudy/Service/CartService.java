package com.jaya.Casestudy.Service;

import com.jaya.Casestudy.DAO.CartDAO;
import com.jaya.Casestudy.Entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    CartDAO cartDAO;

    @Transactional(readOnly = true)
    public List<Cart> getAllProducts() {
        return cartDAO.findAll();
    }

    @Transactional(readOnly = true)
    public Cart getCartProductById(int prod_id) {
        Optional<Cart> op = cartDAO.findById(prod_id);
        if(op.isPresent())
            return op.get();
        return null;
    }

    @Transactional
    public boolean addOrModifyProduct(Cart cart) {
        Cart c = cartDAO.save(cart);
        return c != null;
    }

    @Transactional
    public boolean deleteProductById(int prod_id) {
        long count = cartDAO.count();
        cartDAO.deleteById(prod_id);
        return count>cartDAO.count();
    }

    @Transactional
    public boolean deleteAll() {
        long count = cartDAO.count();
        cartDAO.deleteAll();
        return count>cartDAO.count();
    }
}
