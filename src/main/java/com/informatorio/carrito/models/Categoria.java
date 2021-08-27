package com.informatorio.carrito.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @JsonIgnore
    @ManyToMany(mappedBy = "categorias")
    private Set<Producto> productos = new HashSet<>();

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public Categoria() {

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    
    
}
