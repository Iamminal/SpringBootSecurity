package com.example.springsecuity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/home","/h2-console/*","/css/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .authorizeRequests()
                .antMatchers("/user/**")
                .hasRole("USER")
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

/*    @Bean
    @Override
    public UserDetailsService userDetailsService(){
        UserDetails user=
                User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();

    return new InMemoryUserDetailsManager(user);
    }*/

    @Override
    protected  void configure(AuthenticationManagerBuilder authenticate) {

            authenticate.authenticationProvider(authProvider);

        }
    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder=new BCryptPasswordEncoder();
        return encoder;
    }
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("css/*");
    }
}
