package com.application.kppro_project.other;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class Exception extends ResponseStatusException {

    public Exception() {
        super(HttpStatus.NOT_FOUND);
    }

    public Exception(String e){
        super(HttpStatus.UNAUTHORIZED);
    }
}
