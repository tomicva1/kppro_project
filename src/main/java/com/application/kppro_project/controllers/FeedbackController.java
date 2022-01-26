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
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
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

    @GetMapping("employees/{id}/feedbacks")
    public List<EntityModel<Feedback>> hisFeedbacks(@PathVariable Long id) {
        List<EntityModel<Feedback>> feedback = repository.findByEmployeeId(id).stream().map(assembler::toModel).collect(Collectors.toList());

        return feedback;
    }


    @GetMapping("/feedbacks/{id}")
    public EntityModel<Feedback> one(@PathVariable Long id) {

        Feedback feedback = repository.findById(id) //
                .orElseThrow(() -> new Exception(HttpStatus.NOT_FOUND));
        //.orElseThrow(() -> new Exception("Feedback with this id: " + id + " not exist"));

        return assembler.toModel(feedback);
    }

    // end::get-aggregate-root[]
    @PostMapping("/feedbacks")
    public ResponseEntity<?> newFeedback(@RequestBody Feedback newFeedback) {
        Employee employee = getEmployee();

        if(employee.getId() != newFeedback.getEmployeeId()) {
            newFeedback.setAuthor(employee.getId());
            newFeedback.setCreationTime(getActualDate());

            EntityModel<Feedback> entityModel = assembler.toModel(repository.save(newFeedback));

            return ResponseEntity //
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                    .body(entityModel);
        }
        else{
            throw new Exception(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/feedbacks/{id}")
    public Feedback replaceFeedback(@RequestBody Feedback newFeedback, @PathVariable Long id) {
        Employee employee = getEmployee();

        Feedback feedback =  repository.findById(id).get();

        if(feedback.getAuthor() == employee.getId()) {
                feedback.setNote(newFeedback.getNote());
                feedback.setQuality(newFeedback.getQuality());
                if(newFeedback.getEmployeeId() != null)
                    feedback.setEmployeeId(newFeedback.getEmployeeId());
                return repository.save(feedback);
        }
        else{
            throw new Exception(HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("/feedbacks/{id}")
    public void deleteFeedback(@PathVariable Long id) {

        Feedback feedback = repository.findById(id).get();
        if(feedback.getAuthor() == getEmployee().getId()) {
            repository.deleteById(id);
        }
        else{
            throw new Exception(HttpStatus.METHOD_NOT_ALLOWED);
        }
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

    String getRole(){
        Object[] roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray();
        String role = roles[0].toString();
        return role;
    }

}
