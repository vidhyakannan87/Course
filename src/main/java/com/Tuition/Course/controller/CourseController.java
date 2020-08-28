package com.Tuition.Course.controller;

import com.Tuition.Course.api.request.CourseRequest;
import com.Tuition.Course.model.Course;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/course")
public interface CourseController {

  @PostMapping
  ResponseEntity createCourse(@RequestBody CourseRequest course);

  @GetMapping
  ResponseEntity getAllCourses();

  @GetMapping("/{id}")
  ResponseEntity getCourse(@PathVariable long id);

  @PutMapping("/{id}")
  ResponseEntity updateCourse(@PathVariable long id, @RequestBody CourseRequest course);

  @DeleteMapping("/{id}")
  ResponseEntity deleteCourse(@PathVariable long id);


}
