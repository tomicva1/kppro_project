package com.application.kppro_project.controllers;

import com.application.kppro_project.dao.VacationRepository;
import com.application.kppro_project.models.Vacation;
import com.application.kppro_project.models.VacationModelAssembler;
import com.application.kppro_project.other.NotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    @GetMapping("/vacations")
    public CollectionModel<EntityModel<Vacation>> all() {

        List<EntityModel<Vacation>> vacation = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(vacation, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    @GetMapping("/vacations/{id}")
    public EntityModel<Vacation> one(@PathVariable Long id) {

        Vacation vacation = repository.findById(id) //
                .orElseThrow(() -> new NotFoundException(id));

        return assembler.toModel(vacation);
    }

    @PostMapping("/vacations")
    ResponseEntity<EntityModel<Vacation>> newVacation(@RequestBody Vacation vacation) {
        Vacation vac = new Vacation();
        vac.setVacation(vacation);
        Vacation newVacation = repository.save(vacation);

        return ResponseEntity //
                .created(linkTo(methodOn(VacationController.class).one(newVacation.getId())).toUri()) //
                .body(assembler.toModel(newVacation));
    }

    @PutMapping("/vacations/{id}")
    public Vacation replaceVacation(@RequestBody Vacation vacation, @PathVariable Long id) {

        return repository.findById(id)
                .map(vac -> {
                    vac.setVacation(vacation);
                    return repository.save(vac);
                }).orElseThrow(() -> new NotFoundException(id));
    }

    @DeleteMapping("/vacations/{id}")
    void deleteVacation(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/approved/{id}")
    public Vacation approveVacation(@RequestParam boolean approved, @RequestParam Long approved_by, @PathVariable Long id){
        return repository.findById(id).map(vac -> {
            vac.setApprove(approved, approved_by, getActualDate());
            return repository.save(vac);
        }).orElseThrow(() -> new NotFoundException(id));
    }

    private Date getActualDate(){;
        Date date = new Date();

        return date;
    }
}

