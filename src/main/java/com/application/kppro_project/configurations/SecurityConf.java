package com.application.kppro_project.configurations;

import com.application.kppro_project.dao.EmployeeRepo;
import com.application.kppro_project.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = EmployeeRepo.class)
public class SecurityConf extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService).passwordEncoder(passwordEncoder());
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers("/user/index.html").hasAnyRole("USER","MANAGER")
                .antMatchers("/manager/**").hasRole("MANAGER")
                .and()
                .formLogin().permitAll()
                .and()
                .logout();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
