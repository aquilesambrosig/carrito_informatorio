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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JsonBackReference
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Carrito> carritos = new ArrayList<>();

    private String nombre;

    @Size(min=6, max = 40)  
    private String apellido;

    private String direccion;

   
    @Column(nullable = false,unique = true)
    @Email(message = "Formato de correo invalido")
    private String email;

    
    @Column(nullable = false)
    @NotBlank
    @Size(min=6, max = 40)    
    private String password;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orden> ordenes = new ArrayList<>();
    
    @Column(nullable = true,updatable = false)
    @CreationTimestamp
    private LocalDate fechaAlta;


    
    private String ciudad;

   
    private String provincia;

    
    private String pais;

    
    public Usuario() {

    }

 

    /*---------------GETTERS------------------------------------------------------*/ 
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getPais() {
        return pais;
    }

    public String getProvincia() {
        return provincia;
    }

public List<Carrito> getCarritos() {
    return carritos;
}
    public String getDireccion() {
        return direccion;
    }

    public List<Orden> getOrdenes() {
        return ordenes;
    }

    
    public LocalDate getFechaAlta() {
        return fechaAlta;
    }
    public Long getId() {
        return id;
    }
 
    public String getEmail() {
        return email;
    }

    
    /*---------------SETTERS------------------------------------------------------*/ 
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setOrdenes(List<Orden> ordenes) {
        this.ordenes = ordenes;
    }
    public void setPassword(String password) {
        this.password = password;
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

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}



    

