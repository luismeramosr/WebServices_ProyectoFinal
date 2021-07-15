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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "item")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Item {
	
	@Id
	@Column(name = "barcode", length = 48)
	private String barcode;
	
	@Column(name = " brand", length = 70)
	private String brand;
	
	@Column(name = "name", length = 70)
	private String name;
	
	@Column(name = "description", length = 255)
	private String description;
	
	@Column(name = "price", length = 12)
	private Float price;
	
	@Column(name = "stock", length = 15)
	private int stock;
	
	@Column(name = "stock_min", length = 5)
	private int stock_min;

	@Column(name = "providerId")
	private int providerId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "providerId", insertable = false, updatable = false)
	private Provider provider;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "requestedItems")
	private Set<Request> requests = new HashSet<>();

}
