package com.atmosware.cleanarchwithcqrs.ws.controllers;

import java.util.List;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atmosware.cleanarchwithcqrs.application.features.account.commands.create.CreateAccountTypeCommand;
import com.atmosware.cleanarchwithcqrs.application.features.account.commands.delete.DeleteAccountTypeCommand;
import com.atmosware.cleanarchwithcqrs.application.features.account.queries.getById.GetByIdAccountTypesDto;
import com.atmosware.cleanarchwithcqrs.application.features.account.queries.getById.GetByIdAccountTypesQuery;
import com.atmosware.cleanarchwithcqrs.application.features.account.queries.getall.GetAccountTypesDto;
import com.atmosware.cleanarchwithcqrs.application.features.account.queries.getall.GetAccountTypesQuery;
import com.atmosware.cleanarchwithcqrs.ws.models.create.CreateAccountTypeModel;
import com.atmosware.cleanarchwithcqrs.ws.models.delete.DeleteAccountTypeModel;

@RestController
@RequestMapping("/accounttypes")
public class AccountTypesController {

	private CommandGateway commandGateway;
	private QueryGateway queryGateway;
	
	@Autowired
	public AccountTypesController(CommandGateway commandGateway, QueryGateway queryGateway) {
		this.commandGateway = commandGateway;
		this.queryGateway = queryGateway;
	}
	
	
	@PostMapping
	public void createAccountType(@RequestBody CreateAccountTypeModel createAccountTypeModel) {
		
		CreateAccountTypeCommand command = CreateAccountTypeCommand.builder()
				.price(createAccountTypeModel.getPrice())
				.accountName(createAccountTypeModel.getAccountName())
				.description(createAccountTypeModel.getDescription())
				.build();
		command.setAccountTypeId(UUID.randomUUID().toString());
		this.commandGateway.sendAndWait(command);	
	}
	
	
	@GetMapping("getall")
	public List<GetAccountTypesDto> getAll() {
		
		return this.queryGateway.query(new GetAccountTypesQuery(), ResponseTypes.multipleInstancesOf(GetAccountTypesDto.class)).join();
	}
	
	
	@GetMapping("getById")
	public GetByIdAccountTypesDto getById(@RequestBody GetByIdAccountTypesQuery getByIdAccountTypesQuery) {
		
		return this.queryGateway.query(getByIdAccountTypesQuery, ResponseTypes.instanceOf(GetByIdAccountTypesDto.class)).join();
	}
	
	
	@DeleteMapping("delete")
	public void delete(@RequestBody DeleteAccountTypeModel deleteAccountTypeModal ) {
		
		DeleteAccountTypeCommand command = DeleteAccountTypeCommand.builder()
				.accountTypeId(deleteAccountTypeModal.getAccountTypeId())
				.build();
		this.commandGateway.sendAndWait(command);
		
	}
	
	
//	@PutMapping("update")
//	public void update(@RequestBody UpdateAccountTypeModal updateAccountTypeModal) {
//	
//		UpdateAccountTypeCommand command = UpdateAccountTypeCommand.builder()
//				.price(updateAccountTypeModal.getPrice())
//				.accountName(updateAccountTypeModal.getAccountName())
//				.description(updateAccountTypeModal.getDescription())
//				.build();
//		this.commandGateway.sendAndWait(command);	
//	}
	
}
