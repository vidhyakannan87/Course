package com.Tuition.Course.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Category extends BaseEntity{

  private static final String NAME = "category_id_sequence";
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

  private String description;

  @ManyToMany(fetch = FetchType.LAZY,
          cascade = {
                  CascadeType.PERSIST,
                  CascadeType.MERGE
          },
          mappedBy = "courseCategories")
  private Set<Course> courses = new HashSet<>();
}
