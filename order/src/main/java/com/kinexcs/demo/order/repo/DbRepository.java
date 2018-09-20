package com.kinexcs.demo.order.repo;

import org.springframework.data.repository.CrudRepository;

import com.kinexcs.demo.order.entity.ProductEntity;

public interface DbRepository extends CrudRepository<ProductEntity, Long> {

}
