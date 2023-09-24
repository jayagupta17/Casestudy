package com.jaya.Casestudy.DAO;

import com.jaya.Casestudy.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Product, Integer> {
}
