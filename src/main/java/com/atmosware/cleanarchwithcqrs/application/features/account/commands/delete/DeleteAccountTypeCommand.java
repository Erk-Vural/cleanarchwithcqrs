package com.atmosware.cleanarchwithcqrs.application.features.account.commands.delete;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteAccountTypeCommand {
	
	@TargetAggregateIdentifier
	private String accountTypeId;

}
