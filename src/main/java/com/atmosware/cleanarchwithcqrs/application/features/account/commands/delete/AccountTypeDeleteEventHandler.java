package com.atmosware.cleanarchwithcqrs.application.features.account.commands.delete;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atmosware.cleanarchwithcqrs.persistance.AccountTypeRepository;

@Component
public class AccountTypeDeleteEventHandler {
	
	private AccountTypeRepository accountTypeRepository;

	@Autowired
	public AccountTypeDeleteEventHandler(AccountTypeRepository accountTypeRepository) {
		
		this.accountTypeRepository = accountTypeRepository;
	}
	
	@EventHandler
	public void on(AccountTypeDeletedEvent accountTypeDeletedEvent ) {

		this.accountTypeRepository.deleteById(accountTypeDeletedEvent.getAccountTypeId());
	}

}
