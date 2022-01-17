package com.application.kppro_project.controllers;

import com.application.kppro_project.dao.FeedbackRepository;
import com.application.kppro_project.models.Feedback;
import com.application.kppro_project.models.FeedbackModelAssembler;
import com.application.kppro_project.models.Vacation;
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


public class FeedbackController {

    private final FeedbackRepository repository;
    private final FeedbackModelAssembler assembler;


    public FeedbackController(FeedbackRepository repository, FeedbackModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/feedback/{id}")
    public EntityModel<Feedback> one(@PathVariable Long id) {

        Feedback feedback = repository.findById(id) //
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return assembler.toModel(feedback);
    }

    @GetMapping("/feedback")
    public CollectionModel<EntityModel<Feedback>> all() {

        List<EntityModel<Feedback>> feedback = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(feedback, linkTo(methodOn(FeedbackController.class).all()).withSelfRel());
    }
}
