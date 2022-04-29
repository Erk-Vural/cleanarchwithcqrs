package com.atmosware.cleanarchwithcqrs.ws.models.create;

import lombok.Data;

@Data
public class CreateAccountTypeModel { //REQUEST
	
	private String accountName;

	private double price;

	private String description;

}
