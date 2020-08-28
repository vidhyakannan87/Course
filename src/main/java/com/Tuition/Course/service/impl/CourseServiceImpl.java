package com.Tuition.Course.service.impl;

import com.Tuition.Course.api.request.CourseRequest;
import com.Tuition.Course.model.Category;
import com.Tuition.Course.model.Course;
import com.Tuition.Course.repository.CategoryRepository;
import com.Tuition.Course.repository.CourseRepository;
import com.Tuition.Course.service.CourseService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl  implements CourseService {

  private final CourseRepository courseRepository;
  private final CategoryRepository categoryRepository;
  private final ModelMapper mapper = new ModelMapper();

  private final String COURSE_NOT_FOUND = "Course Not Found";

  public CourseServiceImpl(CourseRepository courseRepository, CategoryRepository categoryRepository) {
    this.courseRepository = courseRepository;
    this.categoryRepository = categoryRepository;
  }

  @Override
  public void addACourse(CourseRequest courseRequest) {

    Course course = mapper.map(courseRequest,Course.class);
    Set<Category> categories = categoryRepository.findByIdIn(courseRequest.getCourseCategories());
    course.setCourseCategories(categories);
    course.getCourseObjectives().stream().peek(objective -> objective.setCourse(course)).collect(Collectors.toList());
    saveCourse(course);
  }

  @Override
  public List<Course> getAllCourses() {
    return courseRepository.findAll();
  }

  @Override
  public Course getACourse(long id) {
    Optional<Course> course = courseRepository.findById(id);

    if(!course.isPresent()){
      throw new EntityNotFoundException(COURSE_NOT_FOUND);
    }
    return course.get();
  }

  @Override
  public void updateACourse(long id, CourseRequest newCourse) {
   Course existingCourse = getACourse(id);

    mapper.getConfiguration()
            .setMatchingStrategy(MatchingStrategies.STRICT)
            .setSkipNullEnabled(true)
            .setPropertyCondition(Conditions.isNotNull());

    TypeMap<CourseRequest, Course> typeMap = mapper.getTypeMap(CourseRequest.class, Course.class);
    if(typeMap == null) {
      mapper.createTypeMap(CourseRequest.class, Course.class)
              .addMappings(m -> m.skip(Course::setCourseCategories));
    }

    mapper.map(newCourse, existingCourse);
    existingCourse.setId(id);
    Set<Category> courseCategories = categoryRepository.findByIdIn(newCourse.getCourseCategories());
    existingCourse.setCourseObjectives(newCourse.getCourseObjectives());
    existingCourse.setCourseCategories(courseCategories);
    existingCourse.getCourseObjectives().stream().peek(objective -> objective.setCourse(existingCourse)).collect(Collectors.toList());

    saveCourse(existingCourse);

  }

  @Override
  public void deleteACourse(long id) {
    Course course = getACourse(id);
    courseRepository.delete(course);

  }

  private void saveCourse(Course course){
    courseRepository.saveAndFlush(course);
  }
}
