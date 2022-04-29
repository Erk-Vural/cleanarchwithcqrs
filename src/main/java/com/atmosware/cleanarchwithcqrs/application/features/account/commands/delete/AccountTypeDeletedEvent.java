package com.atmosware.cleanarchwithcqrs.application.features.account.commands.delete;

import lombok.Data;

@Data
public class AccountTypeDeletedEvent {

	private String accountTypeId;
}
