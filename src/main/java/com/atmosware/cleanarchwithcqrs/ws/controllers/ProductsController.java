package com.atmosware.cleanarchwithcqrs.ws.controllers;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atmosware.cleanarchwithcqrs.application.features.product.commands.CreateProductCommand;
import com.atmosware.cleanarchwithcqrs.ws.models.create.CreateProductModel;

@RestController
@RequestMapping("/products")
public class ProductsController {
	
	private CommandGateway commandGateway;
	private QueryGateway queryGateway;
	
	@Autowired
	public ProductsController(CommandGateway commandGateway, QueryGateway queryGateway) {
		this.commandGateway = commandGateway;
		this.queryGateway = queryGateway;
	}
	
	@PostMapping
	public void createProduct(@RequestBody CreateProductModel createProductModal) {
		
		CreateProductCommand command = CreateProductCommand.builder()
				.price(createProductModal.getPrice())
				.productName(createProductModal.getProductName())
				.description(createProductModal.getDescription())
				.build();
		command.setProductId(UUID.randomUUID().toString());
		this.commandGateway.sendAndWait(command);
	}
	
}
