package com.Tuition.Course.service;

import com.Tuition.Course.api.request.CourseRequest;
import com.Tuition.Course.model.Course;

import java.util.List;

public interface CourseService {

  void addACourse(CourseRequest course);

  List<Course> getAllCourses();

  Course getACourse(long id);

  void updateACourse(long id, CourseRequest newCourse);

  void deleteACourse(long id);

}
