package com.application.kppro_project.controllers;

import com.application.kppro_project.dao.FeedbackRepository;
import com.application.kppro_project.models.Employee;
import com.application.kppro_project.models.Feedback;
import com.application.kppro_project.models.FeedbackModelAssembler;
import com.application.kppro_project.models.Vacation;
import com.application.kppro_project.other.NotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/feedback")
    public CollectionModel<EntityModel<Feedback>> all() {

        List<EntityModel<Feedback>> feedback = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(feedback, linkTo(methodOn(FeedbackController.class).all()).withSelfRel());
    }


    @GetMapping("/feedback/{id}")
    public EntityModel<Feedback> one(@PathVariable Long id) {

        Feedback feedback = repository.findById(id) //
                .orElseThrow(() -> new NotFoundException(id));

        return assembler.toModel(feedback);
    }

    // end::get-aggregate-root[]
    @PostMapping("/feedback")
    ResponseEntity<?> newFeedback(@RequestBody Feedback newFeedback) {
        EntityModel<Feedback> entityModel = assembler.toModel(repository.save(newFeedback));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @PutMapping("/feedback/{id}")
    public Feedback replaceFeedback(@RequestBody Feedback newFeedback, @PathVariable Long id) {

        return repository.findById(id)
                .map(feedback -> {
                    feedback.setNote(newFeedback.getNote());
                    feedback.setQuality(newFeedback.getQuality());
                    feedback.setEmployee_id(newFeedback.getEmployee_id());
                    return repository.save(feedback);
                })
                .orElseGet(() -> {
                    newFeedback.setId(id);
                    return repository.save(newFeedback);
                });
    }

    @DeleteMapping("/feedback/{id}")
    void deleteFeedback(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
