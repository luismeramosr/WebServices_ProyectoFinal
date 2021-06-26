package com.idat.webservices.persistence.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "request_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request_Detail {
	
	@EmbeddedId
	private Request_DetailPK id;

	@Column(name = "quantity", length = 11)
	private int quantity;

}
