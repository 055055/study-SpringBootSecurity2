package com.example.springsecurity2;

import com.example.springsecurity2.account.Account;
import com.example.springsecurity2.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountRunner implements ApplicationRunner {
    @Autowired
    AccountService accountService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = accountService.createAccount("055055","1234");
        System.out.println(account.getUsername()+" pwd "+account.getPassword());
    }
}
