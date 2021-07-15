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
	
	@Column(name = "requestId", length = 20)
	private int requestId;

	@Column(name = "itemId", length = 25)
	private int itemId;

}
