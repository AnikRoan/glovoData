package com.example.glovodata.repository;

import com.example.glovodata.model.data.Order;
import com.example.glovodata.model.data.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order,Integer> {

}
