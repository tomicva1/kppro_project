package com.application.kppro_project.models;

import com.application.kppro_project.controllers.FeedbackController;
import com.application.kppro_project.controllers.VacationController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FeedbackModelAssembler implements RepresentationModelAssembler<Feedback, EntityModel<Feedback>> {

    @Override
    public EntityModel<Feedback> toModel(Feedback feedback) {
        return EntityModel.of(feedback,
                linkTo(methodOn(FeedbackController.class).one(feedback.getId())).withSelfRel(),
                linkTo(methodOn(FeedbackController.class).all()).withRel("feedback"));
    }

    @Override
    public CollectionModel<EntityModel<Feedback>> toCollectionModel(Iterable<? extends Feedback> entities) {
        return null;
    }
}
