package com.idat.webservices.persistence.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
  
  @Id
  @Column(name = "id", length = 15)
  private int id;

  @Column(name = "name", length = 20)
  private String name;

}
