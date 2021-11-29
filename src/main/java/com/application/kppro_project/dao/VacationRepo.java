package com.application.kppro_project.dao;

import com.application.kppro_project.models.Vacation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VacationRepo extends CrudRepository <Vacation, Integer> {

    //List<Vacation> findVacationByEmployeeId(int id);
    //List<Vacation> findVacationByManagerId(int id);
    List<Vacation> findVacationById(int id);

    void deleteById(int id);
}
