package com.application.kppro_project.dao;

import com.application.kppro_project.models.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.EntityModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public interface VacationRepository extends JpaRepository<Vacation, Long> {

    Collection<Vacation> findByEmployeeId(Long id);
}
