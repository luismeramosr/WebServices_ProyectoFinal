package com.idat.webservices.persistence.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "request_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestType {
	
	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "type", length = 35)
	private String type;

}
