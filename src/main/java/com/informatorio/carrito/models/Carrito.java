package com.informatorio.carrito.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Usuario usuario;
    
    
    /*private List<Producto> productos = new ArrayList<>();*/
    @JsonIgnore
    @ManyToMany(mappedBy = "carritos", fetch = FetchType.LAZY)
    private List<Producto> productos = new ArrayList<>();



    private Long cart_total;


    public Carrito() {

}

    public Carrito(Long cart_total) {
    this.cart_total = cart_total;
    }

    @Enumerated(EnumType.STRING)
    private EstadoCarrito estadoCarrito;

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
    
}