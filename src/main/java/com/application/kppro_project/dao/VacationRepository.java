package com.application.kppro_project.dao;

import com.application.kppro_project.models.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationRepository extends JpaRepository<Vacation, Long> {

}
