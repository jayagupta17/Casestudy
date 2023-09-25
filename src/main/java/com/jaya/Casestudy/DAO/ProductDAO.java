package com.jaya.Casestudy.DAO;

import com.jaya.Casestudy.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductDAO extends JpaRepository<Product, Integer> {
    @Query("select p from Product p where productName like concat('%', :str, '%')")
    public List<Product> searchProducts(@Param("str") String str);
}
