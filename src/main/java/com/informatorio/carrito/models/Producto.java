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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.CreationTimestamp;
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

   
    
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Carrito> carritos = new ArrayList<>();
    
    @JsonBackReference
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineaDeCarrito> lineasDeCarrito = new ArrayList<>();

    

    public Producto() {

    }

  
    
    @Column(unique=true)
    @Size(max = 16)
    private String codigoInventario;

 /*   @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "producto_precio", referencedColumnName = "id")
    private Precios precio;*/

    @Positive
    private BigDecimal precioUnitario;

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Categoria> categorias = new HashSet<>();
    
    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }
    public Set<Categoria> getCategorias() {
        return categorias;
    }

    @Column(nullable = true,updatable = false)
    @CreationTimestamp
    private LocalDate fechaAlta;

    
    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public List<Carrito> getCarritos() {
        return carritos;
    }
    public String getCodigoInventario() {
        return codigoInventario;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public Long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    public List<LineaDeCarrito> getLineasDeCarrito() {
        return lineasDeCarrito;
    }
    public void setCodigoInventario(String codigoInventario) {
        this.codigoInventario = codigoInventario;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
/*  public void setPrecio(Precios precio) {
        this.precio = precio;
    }

        public Precios getPrecio() {
        return precio;
    }
*/



    
}
