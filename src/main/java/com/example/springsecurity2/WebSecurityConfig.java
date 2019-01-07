package com.example.springsecurity2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/hello").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //http  accept header에 html이 있어서 1차적으로 여기서 걸림
                .and()
                .httpBasic();
                //accept header에 html이 없다면 여기서 걸림.
    }

    //springsecurity는 password를 인코딩을 하고 디코딩을 해서 비밀번호를 관리하는데, 이렇게 하면 비밀번호 encoding안해도된다.
    //보안에 취약하기 때문에 절대 하면 안된다.
/*    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }*/

    //password encoder로 유저정보 저장하기 전에 encoder함
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
