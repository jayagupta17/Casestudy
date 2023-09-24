package com.jaya.Casestudy.DAO;

import com.jaya.Casestudy.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDAO extends JpaRepository<Cart, Integer> {
    public void deleteAll();
}
