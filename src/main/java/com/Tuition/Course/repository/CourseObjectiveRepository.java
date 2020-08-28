package com.Tuition.Course.repository;

import com.Tuition.Course.model.CourseObjective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseObjectiveRepository extends JpaRepository<CourseObjective,Long> {
}
