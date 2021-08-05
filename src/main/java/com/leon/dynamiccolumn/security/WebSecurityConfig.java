package com.leon.dynamiccolumn.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
   //     auth.inMemoryAuthentication()
   //             .withUser("admin")
   //             .password(getPasswordEncoder().encode("Admin1979!"))
   //             .roles("ADMIN");
   // }
 //   @Bean
 //   public PasswordEncoder getPasswordEncoder() {return new BCryptPasswordEncoder();}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
              .antMatchers("/addproject", "/editproject","/deleteproject").hasRole("ADMIN")
              .antMatchers("/**").permitAll()
                      .and().formLogin();


    }


}
