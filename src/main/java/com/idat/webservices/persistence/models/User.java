package com.idat.webservices.persistence.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @Column(name = "id", length = 25)
  private String id;

  @Column(name = "firstName", length = 70)
  private String firstName;

  @Column(name = "lastName", length = 70)
  private String lastName;

  @Column(name = "dni", length = 8)
  private int dni;

  @Column(name = "phone", length = 9)
  private int phone;

  @Column(name = "email", length = 30)
  private String email;
  
  @Column(name = "username", length = 20)
  private String username;
  
  @Column(name = "password", length = 64)
  private String password;

  @Column(name = "idRole", length = 15)
  private int idRole;

  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "idRole", insertable = false, updatable = false)
  private Role role;

}
