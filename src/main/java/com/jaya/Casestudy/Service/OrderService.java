package com.jaya.Casestudy.Service;

import com.jaya.Casestudy.DAO.OrderDAO;
import com.jaya.Casestudy.Entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderDAO orderDAO;

    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderDAO.findAll();
    }

    @Transactional
    public void addOrder(Order order) {
        orderDAO.save(order);
    }
}
