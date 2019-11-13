package com.bjit.spring.security;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Entity
@Builder
public class Department {

  @Id
  @SequenceGenerator(name = "mySeqGen", sequenceName = "depSeq", initialValue = 1, allocationSize = 10)
  @GeneratedValue(generator = "mySeqGen")
  //@GeneratedValue(strategy= GenerationType.AUTO)
  //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
  //@GeneratedValue(strategy= GenerationType.AUTO)
  //@GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;
  @NotBlank(message="Department Name is required")
  private String name;

}
