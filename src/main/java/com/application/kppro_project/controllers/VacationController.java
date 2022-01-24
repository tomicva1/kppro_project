package com.application.kppro_project.controllers;

import com.application.kppro_project.dao.EmployeeRepository;
import com.application.kppro_project.dao.VacationRepository;
import com.application.kppro_project.enums.StatusEnum;
import com.application.kppro_project.models.Employee;
import com.application.kppro_project.models.Vacation;
import com.application.kppro_project.models.VacationModelAssembler;
import com.application.kppro_project.other.Exception;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        Employee employee = getEmployee();

        List<EntityModel<Vacation>> vacation = repository.findByEmployeeId(employee.getId()).stream().map(assembler::toModel).collect(Collectors.toList());

        return vacation;
    }

    @GetMapping("/vacations/{id}")
    public EntityModel<Vacation> one(@PathVariable Long id) {

        Vacation vacation = repository.findById(id) //
                .orElseThrow(() -> new Exception());
        //.orElseThrow(() -> new Exception("Vacation with this id: " + id + " not exist"));

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
        Employee employee = getEmployee();

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
        Employee employee = getEmployee();
        checkIfManager(employee);


        return repository.findById(id)
                .map(vac -> {
                    vac.setVacation(vacation);
                    return repository.save(vac);
                }).orElseThrow(() -> new Exception());
    }

    @DeleteMapping("/vacations/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteVacation(@PathVariable Long id) {
        Employee employee = getEmployee();
        checkIfManager(employee);

        repository.deleteById(id);

        //return "Vacation has been deleted";
    }

    @PutMapping("/approved/{id}")
    public Vacation approveVacation(@RequestParam StatusEnum status, @RequestParam Long updatedBy, @PathVariable Long id){
        return repository.findById(id).map(vac -> {
            vac.setId(id);
            vac.setApprove(status, updatedBy, getActualDate());

            return repository.save(vac);
        }).orElseThrow(() -> new Exception());
    }



    private Date getActualDate(){;
        Date date = new Date();

        return date;
    }

    void checkIfManager(Employee employee){
        if(employee.getRole() != "MANAGER"){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED);
        }
    }

    Employee getEmployee(){
        String principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Employee employee = (Employee) employeeRepository.findByUsername(principal)
                .orElseThrow(() -> new Exception());
        //.orElseThrow(() -> new Exception("The employee with this username: " + principal + " not exist"));

        return employee;
    }
}

