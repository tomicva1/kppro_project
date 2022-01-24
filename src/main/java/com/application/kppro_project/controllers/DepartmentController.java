package com.application.kppro_project.controllers;

import com.application.kppro_project.dao.DepartmentRepository;
import com.application.kppro_project.models.Department;
import com.application.kppro_project.models.DepartmentModelAssembler;
import com.application.kppro_project.other.NotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class DepartmentController {

    private final DepartmentRepository repository;
    private final DepartmentModelAssembler assembler;

    public DepartmentController(DepartmentRepository repository, DepartmentModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/departments")
    public List<EntityModel<Department>> all() {

        List<EntityModel<Department>> department = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return department;
    }

    @GetMapping("/departments/{id}")
    public EntityModel<Department> one(@PathVariable Long id) {

        Department department = repository.findById(id) //
                .orElseThrow(() -> new NotFoundException(id));

        return assembler.toModel(department);
    }

    // end::get-aggregate-root[]
    @PostMapping("/departments")
    ResponseEntity<?> newDepartment(@RequestBody Department newDepartment) {

        EntityModel<Department> entityModel = assembler.toModel(repository.save(newDepartment));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @PutMapping("/departments/{id}")
    public Department replaceDepartment(@RequestBody Department newDepartment, @PathVariable Long id) {

        return repository.findById(id)
                .map(department -> {
                    department.setName(newDepartment.getName());
                    department.setDescription(newDepartment.getDescription());
                    department.setMap(newDepartment.getMap());
                    return repository.save(department);
                })
                .orElseGet(() -> {
                    newDepartment.setId(id);
                    return repository.save(newDepartment);
                });
    }

    @DeleteMapping("/departments/{id}")
    void deleteDepartment(@PathVariable Long id) {

        repository.deleteById(id);
        //return "Department has been deleted";
    }


}
