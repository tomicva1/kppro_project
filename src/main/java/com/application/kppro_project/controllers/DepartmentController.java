package com.application.kppro_project.controllers;

import com.application.kppro_project.dao.DepartmentRepository;
import com.application.kppro_project.models.Department;
import com.application.kppro_project.models.DepartmentModelAssembler;
import com.application.kppro_project.other.EmployeeNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


public class DepartmentController {

    private final DepartmentRepository repository;
    private final DepartmentModelAssembler assembler;

    public DepartmentController(DepartmentRepository repository, DepartmentModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/department/{id}")
    public EntityModel<Department> one(@PathVariable Long id) {

        Department department = repository.findById(id) //
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return assembler.toModel(department);
    }

    @GetMapping("/department")
    public CollectionModel<EntityModel<Department>> all() {

        List<EntityModel<Department>> department = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(department, linkTo(methodOn(DepartmentController.class).all()).withSelfRel());
    }


}
