package com.Tuition.Course.api.request;

import com.Tuition.Course.model.Category;
import com.Tuition.Course.model.CourseObjective;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.*;

@Data
public class CourseRequest {

  private String name;

  private String description;

  private String prerequisite;

  private String targetedUserGroup;

  private float price;

  private String courseInclusions;

  private Set<Long> courseCategories;

  private List<CourseObjective> courseObjectives;

}
