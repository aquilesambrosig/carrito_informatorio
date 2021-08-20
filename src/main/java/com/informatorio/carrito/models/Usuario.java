package com.informatorio.carrito.models;

import java.security.Timestamp;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Carrito> carritos = new ArrayList<>();

    private String nombre;

    private String apellido;

    private String direccion;
    
    @Column(nullable = true,updatable = false)
    private LocalDate fechaAlta  = LocalDate.now();


 

    /*---------------GETTERS------------------------------------------------------*/ 
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

public List<Carrito> getCarritos() {
    return carritos;
}
    public String getDireccion() {
        return direccion;
    }

    
    public LocalDate getFechaAlta() {
        return fechaAlta;
    }
    public Long getId() {
        return id;
    }
    
    /*---------------SETTERS------------------------------------------------------*/ 
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
 /*   public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }*/
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /*public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }*/
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public void setId(Long id) {
        this.id = id;

    
    }
}



    

