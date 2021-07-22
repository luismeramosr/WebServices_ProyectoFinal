package com.idat.webservices.persistence.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "request")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    @Id
    @Column(name = "id", length = 20)
    private int id;

	@Column(name = "userId", length = 15)
	private int userId;

    @Column(name = "typeId", length = 20)
    private int typeId;

    @Column(name = "total")
    private Float total;

	@Column(name = "request_datetime")
	private LocalDateTime request_datetime;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "typeId", insertable = false, updatable = false)
	private RequestType requestType;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private User user;

	@ManyToMany
	@JoinTable(
		name = "request_detail",
		joinColumns = @JoinColumn(name = "requestId"),
		inverseJoinColumns = @JoinColumn(name = "barcode")
	)	
	private Set<Item> requestedItems = new HashSet<>();
    
}
