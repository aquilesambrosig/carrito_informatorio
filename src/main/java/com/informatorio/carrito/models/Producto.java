package com.informatorio.carrito.models;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

@Entity 
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    private String nombre;

    @Size(max = 255)
    private String descripcion;

    //private BigDecimal precioUnitario;

    
   // private Carrito carrito;
    
    @Column(unique=true)
    @Size(max = 16)
    private String codigoInventario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "producto_precio", referencedColumnName = "id")
    private Precios precio;






    
}
