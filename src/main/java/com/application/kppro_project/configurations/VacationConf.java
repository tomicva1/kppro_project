package com.application.kppro_project.configurations;

import com.application.kppro_project.models.Vacation;
import com.application.kppro_project.services.VacationService;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public class VacationConf {
    @Autowired
    private VacationService vacationService;

    private String message = "";

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
}
