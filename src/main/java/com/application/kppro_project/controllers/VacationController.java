package com.application.kppro_project.controllers;

import com.application.kppro_project.dao.EmployeeRepository;
import com.application.kppro_project.dao.VacationRepository;
import com.application.kppro_project.enums.StatusEnum;
import com.application.kppro_project.models.Employee;
import com.application.kppro_project.models.Vacation;
import com.application.kppro_project.models.VacationModelAssembler;
import com.application.kppro_project.other.NotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class VacationController {

    private final VacationRepository repository;
    private final VacationModelAssembler assembler;
    private final EmployeeRepository employeeRepository;

    public VacationController(VacationRepository repository, VacationModelAssembler assembler, EmployeeRepository employeeRepository) {
        this.repository = repository;
        this.assembler = assembler;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/vacations/emp")
    public List<EntityModel<Vacation>> myVacations() {
        String principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Employee employee = (Employee) employeeRepository.findByUsername(principal)
                .orElseThrow(() -> new NotFoundException(principal));

        List<EntityModel<Vacation>> vacation = repository.findByEmployeeId(employee.getId()).stream().map(assembler::toModel).collect(Collectors.toList());

        return vacation;
    }

    @GetMapping("/vacations/{id}")
    public EntityModel<Vacation> one(@PathVariable Long id) {

        Vacation vacation = repository.findById(id) //
                .orElseThrow(() -> new NotFoundException(id));

        return assembler.toModel(vacation);
    }

    @GetMapping("/vacations")
    public List<EntityModel<Vacation>> all() {

        List<EntityModel<Vacation>> vacation = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        //return CollectionModel.of(vacation, linkTo(methodOn(VacationController.class).all()).withSelfRel());*/
        return vacation;
    }

    @PostMapping("/vacations")
    public ResponseEntity<EntityModel<Vacation>> newVacation(@RequestBody Vacation vacation) {
        String principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Employee employee = (Employee) employeeRepository.findByUsername(principal)
                .orElseThrow(() -> new NotFoundException(principal));

        Vacation vac = new Vacation();
        vac.setVacation(vacation);
        vac.setEmployeeId(employee.getId());
        vac.setStatus(StatusEnum.WAITING);
        Vacation newVacation = repository.save(vac);

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

        //return "Vacation has been deleted";
    }

    @PutMapping("/approved/{id}")
    public Vacation approveVacation(@RequestParam StatusEnum status, @RequestParam Long updatedBy, @PathVariable Long id){
        return repository.findById(id).map(vac -> {
            vac.setId(id);
            vac.setApprove(status, updatedBy, getActualDate());

            return repository.save(vac);
        }).orElseThrow(() -> new NotFoundException(id));
    }



    private Date getActualDate(){;
        Date date = new Date();

        return date;
    }
}

