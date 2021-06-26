package com.idat.webservices.persistence.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "providers")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Provider {
	
	@Id
	@Column(name = "id", length = 6)
	private int id;
	
	@Column(name = "name", length = 50)
	private String name;
	
	@Column(name = "phone", length = 9)
	private int phone;

	@OneToMany(mappedBy = "provider", cascade = {CascadeType.ALL})
	private Set<Product> products = new HashSet<>();
}
