package com.jaya.Casestudy.DAO;

import com.jaya.Casestudy.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<Order, String> {
}
