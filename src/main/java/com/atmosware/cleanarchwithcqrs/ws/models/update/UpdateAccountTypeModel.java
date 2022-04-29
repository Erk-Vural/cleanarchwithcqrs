package com.atmosware.cleanarchwithcqrs.ws.models.update;

import lombok.Data;

@Data
public class UpdateAccountTypeModel {
	
	private String accountTypeId;
	
	private String accountName;
	
	private double price;

	private String description;

}
