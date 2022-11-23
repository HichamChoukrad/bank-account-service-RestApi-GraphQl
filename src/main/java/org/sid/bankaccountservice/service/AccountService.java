package org.sid.bankaccountservice.service;

import org.sid.bankaccountservice.dto.BankAccountResponseDTO;

public interface AccountService {
     public BankAccountResponseDTO addAccount(BankAccountResponseDTO bankAccountDTO);


     //update pour updateAccount de BankAccountGraphQLController
     BankAccountResponseDTO updateAccount(String id, BankAccountResponseDTO bankAccountDTO);
}

