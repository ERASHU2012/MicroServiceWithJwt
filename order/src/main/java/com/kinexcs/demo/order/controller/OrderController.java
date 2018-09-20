package com.kinexcs.demo.order.controller;

import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kinexcs.demo.common.constant.ApiConstent;
import com.kinexcs.demo.common.constant.DataNotFoundException;
import com.kinexcs.demo.common.dto.Product;
import com.kinexcs.demo.order.entity.ProductEntity;
import com.kinexcs.demo.order.repo.DbRepository;

@Api
@RestController
@RequestMapping("/order")
public class OrderController {
	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(OrderController.class);
	@Autowired
	DbRepository dbRepo;

	@RequestMapping(value = "/getProduct", method = RequestMethod.GET)
	public ResponseEntity<Product> getProduct(@RequestParam(name = ApiConstent.Header.PRODUCT_ID) long productId,
			@RequestParam(name = ApiConstent.Header.ORDER_ADDITIONAL_DATA_REQUIRED) boolean additionalDataRequired) throws DataNotFoundException {
		ProductEntity prodEn = dbRepo.findOne(productId);

		if (prodEn == null) {
			throw new DataNotFoundException("Progoct not founded with product Id " + productId);
		}

		return new ResponseEntity<>(prodEn.toDto(additionalDataRequired), HttpStatus.OK);
	}
}
