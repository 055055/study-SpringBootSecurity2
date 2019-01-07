package com.example.springsecurity2;

import com.example.springsecurity2.account.Account;
import com.example.springsecurity2.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
//userdetailsservice를 implements해야지 우리가 만드는 user정보로 로그인처리가능
public class AccountService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account createAccount(String username, String password){
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        return accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> byUserName = accountRepository.findByUsername(username);
       Account account = byUserName.orElseThrow(()-> new UsernameNotFoundException(username));
        //일치하는 username이 없으면 exception 처리
        return new User(account.getUsername(), account.getPassword(), authorities());
        //return 값으로 User반환, 이건 스프링시큐리티에서 UserDetails라는 인터페이스에 정의되어 있다. User안에 정의 되어 있음.
    }

    //3번쨰 인자인 authorities는 어떤 권한을 가진 유저라는 것을 셋팅을 해줌.
    private Collection<? extends GrantedAuthority> authorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
