package com.idat.webservices.persistence.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request_DetailPK implements Serializable {
	
	@Column(name = "idRequest", length = 20)
	private String idRequest;

	@Column(name = "idProduct", length = 25)
	private String idProduct;

}
