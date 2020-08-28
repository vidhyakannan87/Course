package com.Tuition.Course.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
public class Course extends BaseEntity{

  private static final String NAME = "course_id_sequence";
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = NAME)
  @SequenceGenerator(
          name = NAME,
          sequenceName = NAME,
          initialValue = INITIAL_VALUE,
          allocationSize = ALLOCATION_SIZE
  )
  @JsonIgnore
  @Id
  private long id;

  private String name;

  @Column(length = 5000)
  private String description;

  @Column(length = 5000)
  private String prerequisite;

  @Column(length = 5000)
  private String targetedUserGroup;

  private float price;

  @Column(length = 5000)
  private String courseInclusions;

  @Column(nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  @JsonIgnore
  private Date createdAt = new Date();

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @LastModifiedDate
  @JsonIgnore
  private Date updatedAt = new Date();

  @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinTable(
          name = "course_category",
          joinColumns = @JoinColumn(name = "course_Id"),
          inverseJoinColumns = @JoinColumn(name = "category_Id")
  )
  @NotFound(action = NotFoundAction.IGNORE)
  private Set<Category> courseCategories = new HashSet<>();

  @OneToMany(mappedBy = "course", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<CourseObjective> courseObjectives = new ArrayList<>();

  public void setCourseObjectives(List<CourseObjective> objectiveList) {
    this.courseObjectives.clear();
    if (objectiveList != null) {
      this.courseObjectives.addAll(objectiveList);
    }
  }

}
