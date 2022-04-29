package com.atmosware.cleanarchwithcqrs.application.features.account.queries.getall;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAccountTypesDto {

	private String accountTypeId;

	private String accountName;

	private double price;

	private String description;

}
