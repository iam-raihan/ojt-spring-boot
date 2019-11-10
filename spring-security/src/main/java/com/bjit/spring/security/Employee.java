package com.bjit.spring.security;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Entity
@Builder
public class Employee {
  
  @Id
  @SequenceGenerator(name = "mySeqGen", sequenceName = "empSeq", initialValue = 1, allocationSize = 10)
  @GeneratedValue(generator = "mySeqGen")
  //@GeneratedValue(strategy= GenerationType.AUTO)
  //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
  //@GeneratedValue(strategy= GenerationType.AUTO)
  //@GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;
  @NotBlank(message="Employee Name is required")
  private String name;
  private String address;

  @ManyToOne(targetEntity = Department.class)
  @NotNull(message="Department is required")
  //@Basic(optional = false, fetch = FetchType.LAZY)
  private Department department;

}
