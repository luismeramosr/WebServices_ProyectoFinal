package com.idat.webservices.persistence.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "products")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	@Id
	@Column(name = "barcode", length = 25)
	private String barcode;
	
	@Column(name = " brand", length = 70)
	private String brand;
	
	@Column(name = "name", length = 70)
	private String name;
	
	@Column(name = "description", length = 255)
	private String description;
	
	@Column(name = "price", length = 12)
	private Float price;
	
	@Column(name = "stock", length = 10)
	private int stock;
	
	@Column(name = "stock_min", length = 10)
	private int stock_min;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "provider_id")
	private Provider provider;
	
	

}
