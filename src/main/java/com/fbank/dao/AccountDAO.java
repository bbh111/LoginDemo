package com.fbank.dao;

import com.fbank.entities.Account;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;


@Data
@Component
public class AccountDAO {
    private SessionFactory sessionFactory;

    public AccountDAO(SessionFactory session) {
        this.sessionFactory = session;
    }

    public Account createNewAccount(Account account) {
        sessionFactory.getCurrentSession().persist(account);
        return account;
    }

    public Account getAccountByUsername(String username, String password) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Account where username =: username and password=:password")
                .setParameter("username", username).setParameter("password",password);
        Account rs = (Account) query.uniqueResult();
        return rs;
    }

    public void updateAccount(Account account) {
        sessionFactory.getCurrentSession().update(account);
    }

    public Account getAccountByUsername(String username){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Account where username=:username")
                .setParameter("username",username);
        return (Account) query.uniqueResult();
    }

}
