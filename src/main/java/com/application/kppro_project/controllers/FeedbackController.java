package com.application.kppro_project.controllers;

import com.application.kppro_project.dao.EmployeeRepository;
import com.application.kppro_project.dao.FeedbackRepository;
import com.application.kppro_project.models.Employee;
import com.application.kppro_project.models.Feedback;
import com.application.kppro_project.models.FeedbackModelAssembler;
import com.application.kppro_project.models.Vacation;
import com.application.kppro_project.other.Exception;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class FeedbackController {

    private final FeedbackRepository repository;
    private final FeedbackModelAssembler assembler;
    private final EmployeeRepository employeeRepository;


    public FeedbackController(FeedbackRepository repository, FeedbackModelAssembler assembler, EmployeeRepository employeeRepository) {
        this.repository = repository;
        this.assembler = assembler;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/feedbacks")
    public List<EntityModel<Feedback>> all() {

        List<EntityModel<Feedback>> feedback = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return feedback;
    }

    @GetMapping("/feedbacks/emp")
    public List<EntityModel<Feedback>> myFeedbacks() {
        Employee employee = getEmployee();

        System.out.println(employee.getId());
        List<EntityModel<Feedback>> feedback = repository.findByEmployeeId(employee.getId()).stream().map(assembler::toModel).collect(Collectors.toList());

        return feedback;
    }


    @GetMapping("/feedbacks/{id}")
    public EntityModel<Feedback> one(@PathVariable Long id) {

        Feedback feedback = repository.findById(id) //
                .orElseThrow(() -> new Exception());
        //.orElseThrow(() -> new Exception("Feedback with this id: " + id + " not exist"));

        return assembler.toModel(feedback);
    }

    // end::get-aggregate-root[]
    @PostMapping("/feedbacks")
    public ResponseEntity<?> newFeedback(@RequestBody Feedback newFeedback) {
        Employee employee = getEmployee();

        newFeedback.setAuthor(employee.getId());
        newFeedback.setCreationTime(getActualDate());

        EntityModel<Feedback> entityModel = assembler.toModel(repository.save(newFeedback));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @PutMapping("/feedbacks/{id}")
    public Feedback replaceFeedback(@RequestBody Feedback newFeedback, @PathVariable Long id) {

        return repository.findById(id)
                .map(feedback -> {
                    feedback.setNote(newFeedback.getNote());
                    feedback.setQuality(newFeedback.getQuality());
                    feedback.setEmployeeId(newFeedback.getEmployeeId());
                    return repository.save(feedback);
                })
                .orElseGet(() -> {
                    newFeedback.setId(id);
                    return repository.save(newFeedback);
                });
    }

    @DeleteMapping("/feedbacks/{id}")
    public void deleteFeedback(@PathVariable Long id) {

        repository.deleteById(id);

        //return "Feedback has been deleted";
    }

    private Date getActualDate(){;
        Date date = new Date();

        return date;
    }

    Employee getEmployee(){
        String principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Employee employee = (Employee) employeeRepository.findByUsername(principal)
                .orElseThrow(() -> new Exception());
        //.orElseThrow(() -> new Exception("The employee with this username: " + principal + " not exist"));

        return employee;
    }
}
