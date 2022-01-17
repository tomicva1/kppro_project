package com.application.kppro_project.models;

import com.application.kppro_project.controllers.EmployeeController;
import com.application.kppro_project.dao.EmployeeRepository;
import com.application.kppro_project.other.NotFoundException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.aspectj.weaver.ast.Call;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomAuthProvider implements AuthenticationProvider {

    private EmployeeRepository repository;

    CustomAuthProvider(EmployeeRepository repository) {

        this.repository = repository;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        boolean result = false;

        result = checkAuthentification(name, password);
        if(result) {
            // use the credentials
            // and authenticate against the third-party system
            return new UsernamePasswordAuthenticationToken(
                    name, password, new ArrayList<>());
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public boolean checkAuthentification(String username, String pwd) {
        Employee employee = (Employee) repository.findByUsername(username) //
                .orElseThrow(() -> new NotFoundException(username));

        boolean login = pwdMatch(employee.getPassword(), pwd);
        if(login) {
            return true;
        }
        else{
            return false;
        }

    }
/*
    private static String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
*/
    private static boolean pwdMatch(String userPwd, String pwd){
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();

        return b.matches(pwd, userPwd);
    }
}