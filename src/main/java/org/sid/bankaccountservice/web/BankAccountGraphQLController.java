package org.sid.bankaccountservice.web;

import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.sid.bankaccountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class BankAccountGraphQLController {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountService accountService;

    @QueryMapping
    public List<BankAccount> accountsList() {   //Voir schema.graphql (accountsList)
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount bankAccountById(@Argument String id) { // Voir schema.graphql (bankAccountById(id:String))
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));
    }

    //Créons une méthode qui permet d'ajouter un compte
    @MutationMapping //Pour toutes les opérations de modéfications
    public BankAccountResponseDTO addAccount(@Argument BankAccountResponseDTO bankAccount) { // Voir schema.graphql Type Mutation (addAccount) . et on va créer un objet bankAccountDTO car il existe déja BankAccountDTO
      return   accountService.addAccount(bankAccount);

    }

    //update un compte
    @MutationMapping //Pour toutes les opérations de modéfications
    public BankAccountResponseDTO updateAccount(@Argument String id, @Argument BankAccountResponseDTO bankAccount) { // Voir schema.graphql Type Mutation (updateAccount)
        return accountService.updateAccount(id, bankAccount);
    }

    //supprimer un compte
    @MutationMapping
    public Boolean  deleteAccount(@Argument String id) { // Voir schema.graphql Type Mutation (deleteAccount)
       bankAccountRepository.deleteById(id);
       return true;
    }

    }



// un record est un objet dont lequel on spécifie les paramètres de constructeur
//record BankAccountDTO(Double balance, String type, String currency) {}
/* --> équivalant à:
@Data @NoArgsConstructor @AllArgsConstructor
public class BankAccountDTO {
    private String type;
    private Double balance;
    private String currency;
   }
*/
