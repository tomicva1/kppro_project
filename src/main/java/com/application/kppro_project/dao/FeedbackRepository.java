package com.application.kppro_project.dao;

import com.application.kppro_project.models.Feedback;
import com.application.kppro_project.models.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    Collection<Feedback> findByEmployeeId(Long id);

}
