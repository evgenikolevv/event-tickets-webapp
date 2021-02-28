package com.webapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
