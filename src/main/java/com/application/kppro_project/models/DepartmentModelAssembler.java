package com.application.kppro_project.models;

import com.application.kppro_project.controllers.DepartmentController;
import com.application.kppro_project.controllers.FeedbackController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class DepartmentModelAssembler implements RepresentationModelAssembler<Department, EntityModel<Department>> {

    @Override
    public EntityModel<Department> toModel(Department department) {
        EntityModel<Department> departmentModel = EntityModel.of(department,
                linkTo(methodOn(DepartmentController.class).one(department.getId())).withSelfRel(),
                linkTo(methodOn(DepartmentController.class).all()).withRel("department"));
        return null;
    }

    @Override
    public CollectionModel<EntityModel<Department>> toCollectionModel(Iterable<? extends Department> entities) {
        return null;
    }
}
