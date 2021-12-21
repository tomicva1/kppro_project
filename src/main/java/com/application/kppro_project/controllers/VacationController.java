package com.application.kppro_project.controllers;

import com.application.kppro_project.dao.VacationRepository;
import com.application.kppro_project.models.Employee;
import com.application.kppro_project.models.Vacation;
import com.application.kppro_project.models.VacationModelAssembler;
import com.application.kppro_project.other.EmployeeNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class VacationController {

    private final VacationRepository repository;
    private final VacationModelAssembler assembler;

    public VacationController(VacationRepository repository, VacationModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @PostMapping("/vacation")
    ResponseEntity<EntityModel<Vacation>> newVacation(@RequestBody Vacation vacation) {

        vacation.setVacation();
        Vacation newVacation = repository.save(vacation);

        return ResponseEntity //
                .created(linkTo(methodOn(VacationController.class).one(newVacation.getId())).toUri()) //
                .body(assembler.toModel(newVacation));
    }

    @GetMapping("/vacation/{id}")
    public EntityModel<Vacation> one(@PathVariable Long id) {

        Vacation vacation = repository.findById(id) //
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return assembler.toModel(vacation);
    }

    @GetMapping("/vacation")
    public CollectionModel<EntityModel<Vacation>> all() {

        List<EntityModel<Vacation>> vacation = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(vacation, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }
}

