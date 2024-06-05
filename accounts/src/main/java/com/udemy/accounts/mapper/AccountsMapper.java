package com.udemy.accounts.mapper;

import com.udemy.accounts.dto.AccountsDTO;
import com.udemy.accounts.entity.Accounts;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 03/06/2024 14:43
 * @Description : convert the raw data from database(repository level) to DTO
 */
public class AccountsMapper {
    public static AccountsDTO mapToAccountsDTO(Accounts accounts, AccountsDTO accountsDTO) {
        accountsDTO.setAccountNumber(accounts.getAccountNumber());
        accountsDTO.setAccountType(accounts.getAccountType());
        accountsDTO.setBranchAddress(accounts.getBranchAddress());
        return accountsDTO;
    }
    public static Accounts mapToAccounts(AccountsDTO accountsDTO,Accounts accounts) {
        accounts.setAccountNumber(accountsDTO.getAccountNumber());
        accounts.setAccountType(accountsDTO.getAccountType());
        accounts.setBranchAddress(accountsDTO.getBranchAddress());
        return accounts;
    }
}
