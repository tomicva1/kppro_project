package com.application.kppro_project.services;

import com.application.kppro_project.dao.VacationRepo;
import com.application.kppro_project.models.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class VacationService {

    @Autowired
    private VacationRepo vacationRepo;

    public Collection<Vacation> findAllVacationForEmployee(int id){
        List<Vacation> vacation = new ArrayList<Vacation>();
        for(Vacation vac : vacationRepo.findVacationById(id)){
            vacation.add(vac);
        }
        return vacation;
    }

    public void deleteVacation(int id) {
        vacationRepo.deleteById(id);
    }

    public void saveVacation(Vacation vacation){
        vacationRepo.save(vacation);
    }

    public Vacation getVacation(int id) {
        return vacationRepo.findById(id).get();
    }


}
