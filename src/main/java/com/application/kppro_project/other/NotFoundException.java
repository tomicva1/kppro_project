package com.application.kppro_project.other;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Could not find id " + id);
    }

    public NotFoundException(String name) {
        super("Could not find " + name);
    }
}
