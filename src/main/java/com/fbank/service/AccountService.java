package com.fbank.service;

import com.fbank.dao.AccountDAO;
import com.fbank.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;
import java.util.Optional;

@Controller
public class AccountService {
    AccountDAO accountDAO;

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Transactional
    public Account createNewAccount(Account account) {
        Optional<Account> optionalAccount = Optional.empty();
        optionalAccount = Optional.of(accountDAO.createNewAccount(account));
        return optionalAccount.orElseThrow(() -> new AccountException("Error happen"));
    }

    @Transactional
    public Account getAccountByUsernameAndPassword(String username, String password) {
        Account account = null;
        account = accountDAO.getAccountByUsername(username, password);
        return account;
    }

    @Transactional
    public void updateUser(Account account) {
        accountDAO.updateAccount(account);
    }

    @Transactional
    public Account getAccountByUsername(String username){
        return accountDAO.getAccountByUsername(username);
    }
}

class AccountException extends RuntimeException {
    public AccountException(String s) {
        super(s);
    }
}
