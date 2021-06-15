package com.idat.webservices.persistence.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
public class DetailRequest {

    @Id
    @Column(name = "id", length = 20)
    private String idDetalle;
    
    @Column(name = "cant")
    private int cant;

    @Column(name = "subtotal")
    private Float subtotal;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_barcode")
    private Product product;
    
}
