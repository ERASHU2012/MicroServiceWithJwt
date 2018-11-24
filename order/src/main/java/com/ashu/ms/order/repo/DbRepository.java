package com.ashu.ms.order.repo;

import org.springframework.data.repository.CrudRepository;

import com.ashu.ms.order.entity.ProductEntity;

public interface DbRepository extends CrudRepository<ProductEntity, Long> {

}
