package com.application.kppro_project.controllers;

import com.application.kppro_project.models.Employee;
import com.application.kppro_project.models.Vacation;
import com.application.kppro_project.services.UserService;
import com.application.kppro_project.services.VacationService;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
public class VacationController {
    @Autowired
    private VacationService vacationService;

    @Autowired
    private UserService userService;

    private String message = "";

    @GetMapping(value = "/user/vacationRequest")
    public String showAddVacation(Model model){
        Vacation vacation = new Vacation();
        model.addAttribute("vacation", vacation);
        populateWithData(model);
        System.out.println("jsem tadyyy");
        return "user/vacationRequest";
    }

    @GetMapping(value = "/allVacation")
    public String showAllVacation(Model model){
        Vacation vacation = new Vacation();
        model.addAttribute("vacation", vacation);
        populateWithData(model);
        System.out.println("jsem v all vacation");
        return "allVacation";
    }

    @RequestMapping(value = "/user/deleteVacation/{id}")
    public String deleteVacation(@PathVariable(name = "id") int id) {
        vacationService.deleteVacation(id);
        message = "Dovolená byla smazána";
        return "redirect:/allVacation";
    }

    @RequestMapping(value = "/user/saveVacation", method = RequestMethod.POST)
    public String saveVacation(@Valid @ModelAttribute("vacation") Vacation vacation, BindingResult bindingResult, Model model) {
        /*if(!vacationService.isUnique(vacation.getStartDate())){
            FieldError error = new FieldError("addDomain", "name",
                    "Domain name already exists");
            bindingResult.addError(error);
        }*/
        //TODO porovnat zda nějaká dovlená aspoŇ částečně nepokrývá stejný datum..

        vacationService.saveVacation(vacation);
        message = "Dovolená zadána";
        return "redirect:/allVacation";
    }

    private void populateWithData(Model model){
        Collection<Employee> employee = userService.findAllEmployees();
        model.addAttribute("employee", employee);
    }
}
