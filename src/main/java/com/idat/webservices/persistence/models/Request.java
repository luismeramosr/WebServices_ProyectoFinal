package com.idat.webservices.persistence.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "requests")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    @Id
    @Column(name = "id", length = 20)
    private String id;

    @Column(name = "tipe", length = 20)
    private String tipe;

    @Column(name = "saldo_Total")
    private Float saldo_Total;
    
}
