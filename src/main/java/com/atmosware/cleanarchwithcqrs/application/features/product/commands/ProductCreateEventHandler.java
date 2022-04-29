package com.atmosware.cleanarchwithcqrs.application.features.product.commands;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atmosware.cleanarchwithcqrs.domain.Product;
import com.atmosware.cleanarchwithcqrs.persistance.ProductRepository;

@Component
public class ProductCreateEventHandler {
	
	private ProductRepository productRepository;

	@Autowired
	public ProductCreateEventHandler(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@EventHandler
	public void on(ProductCreatedEvent productCreatedEvent) {
		
		Product product = new Product();
		BeanUtils.copyProperties(productCreatedEvent, product);
		this.productRepository.save(product);
	}

}
