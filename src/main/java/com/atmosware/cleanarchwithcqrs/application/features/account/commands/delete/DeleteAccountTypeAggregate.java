package com.atmosware.cleanarchwithcqrs.application.features.account.commands.delete;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class DeleteAccountTypeAggregate {
	
	@AggregateIdentifier
	private String identifier=UUID.randomUUID().toString();
	
	private String accountTypeId;

	public DeleteAccountTypeAggregate() {

	}

	@CommandHandler
	public DeleteAccountTypeAggregate (DeleteAccountTypeCommand command) {
		
		AccountTypeDeletedEvent accountTypeDeletedEvent = new AccountTypeDeletedEvent();
		
		BeanUtils.copyProperties(command, accountTypeDeletedEvent);
		
		AggregateLifecycle.apply(accountTypeDeletedEvent);
	}
	
	@EventSourcingHandler
	public void on(AccountTypeDeletedEvent accountTypeDeletedEvent) {

		this.accountTypeId = accountTypeDeletedEvent.getAccountTypeId();
	}
	
}
