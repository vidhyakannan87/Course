package com.Tuition.Course.controller.impl;

import com.Tuition.Course.api.request.CourseRequest;
import com.Tuition.Course.controller.CourseController;
import com.Tuition.Course.model.Course;
import com.Tuition.Course.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseControllerImpl implements CourseController {

  private final CourseService courseService;

  public CourseControllerImpl(CourseService courseService) {
    this.courseService = courseService;
  }

  @Override
  public ResponseEntity createCourse(@RequestBody CourseRequest course) {
    courseService.addACourse(course);
    return ResponseUtility.buildOkResponse();
  }

  @Override
  public ResponseEntity getAllCourses() {
    return ResponseUtility.buildOkResponse(courseService.getAllCourses());
  }

  @Override
  public ResponseEntity getCourse(@PathVariable long id) {
    return ResponseUtility.buildOkResponse(courseService.getACourse(id));
  }

  @Override
  public ResponseEntity updateCourse(@PathVariable long id,@RequestBody CourseRequest course) {
    courseService.updateACourse(id,course);
    return ResponseUtility.buildOkResponse();
  }

  @Override
  public ResponseEntity deleteCourse(@PathVariable long id) {
    courseService.deleteACourse(id);
    return ResponseUtility.buildOkResponse();
  }
}
