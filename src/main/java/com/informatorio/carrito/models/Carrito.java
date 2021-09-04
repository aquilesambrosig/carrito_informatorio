package com.informatorio.carrito.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Usuario usuario;
    
    
   
    
    @ManyToMany(mappedBy = "carritos", fetch = FetchType.LAZY)
    private List<Producto> productos = new ArrayList<>();

    
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineaDeCarrito> lineasDeCarrito = new ArrayList<>();



    private BigDecimal cart_total;


    public Carrito() {

    }


    @Enumerated(EnumType.STRING)
    private EstadoCarrito estadoCarrito;

    public void setCart_total(BigDecimal cart_total) {
        this.cart_total = cart_total;
    }
    public BigDecimal getCart_total() {
        
           if (this.estadoCarrito==EstadoCarrito.ACTIVO) {
               
             for (LineaDeCarrito lineaDeCarrito : lineasDeCarrito) {
            cart_total = cart_total.add(lineaDeCarrito.getSubtotal());     
            }
            return cart_total;
        }
        return cart_total;
        
    }
    @Column(nullable = true,updatable = false)
    @CreationTimestamp
    private LocalDate fechaCreacion;

    public List<LineaDeCarrito> getLineasDeCarrito() {
        return lineasDeCarrito;
    }

    public EstadoCarrito getEstadoCarrito() {
        return estadoCarrito;
    }
    public Long getId() {
        return id;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public List<Producto> getProductos() {
        return productos;
    }
    public void setEstadoCarrito(EstadoCarrito estadoCarrito) {
        this.estadoCarrito = estadoCarrito;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    public void agregarLineDeCarrito(LineaDeCarrito lineaDeCarrito) {
        lineasDeCarrito.add(lineaDeCarrito);
        lineaDeCarrito.setCarrito(this);
    }

    public void removerLineDeCarrito(LineaDeCarrito lineaDeCarrito) {
        lineasDeCarrito.remove(lineaDeCarrito);
        lineaDeCarrito.setCarrito(null);
    }
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }
    

}

