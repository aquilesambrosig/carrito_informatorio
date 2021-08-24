package com.informatorio.carrito.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ManyToAny;

@Entity 
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    private String nombre;

    @Size(max = 255)
    private String descripcion;

    private Long precioUnitario;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Carrito> carritos = new ArrayList<>();

    public Producto() {

    }

    public Producto(String description, Long precioUnitario) {
        this.descripcion = description;
        this.precioUnitario=precioUnitario;
        }
    
    @Column(unique=true)
    @Size(max = 16)
    private String codigoInventario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "producto_precio", referencedColumnName = "id")
    private Precios precio;






    
}
