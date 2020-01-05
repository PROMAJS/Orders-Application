package sda.soft.academy.spring.ordersapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
                authorizeRequests()
                .antMatchers("/user/register", "/user/login", "/message", "/user/activation").permitAll()
                .antMatchers("/order/add").authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/user/login")
                .loginProcessingUrl("/user/loginAction")
                .usernameParameter("login")
                .passwordParameter("password")
                .defaultSuccessUrl("/message?messageText=User login successful&messageType=success")
                .permitAll();
    }
}
