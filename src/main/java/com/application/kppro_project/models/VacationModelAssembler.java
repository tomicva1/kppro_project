package com.application.kppro_project.models;

import com.application.kppro_project.controllers.VacationController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class VacationModelAssembler implements RepresentationModelAssembler<Vacation, EntityModel<Vacation>> {

    @Override
    public EntityModel<Vacation> toModel(Vacation vacation) {
        EntityModel<Vacation> vacationModel = EntityModel.of(vacation,
                linkTo(methodOn(VacationController.class).one(vacation.getId())).withSelfRel(),
                linkTo(methodOn(VacationController.class).all()).withRel("vacation"));        return null;
    }

    @Override
    public CollectionModel<EntityModel<Vacation>> toCollectionModel(Iterable<? extends Vacation> entities) {
        return null;
    }
}
