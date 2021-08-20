package com.informatorio.carrito.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Precios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
   
    @JsonIgnore
    @OneToOne(mappedBy = "precio")
    private Producto producto;

    private BigDecimal precioUnitario;



    
    
}
