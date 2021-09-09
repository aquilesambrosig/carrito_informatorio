package com.informatorio.carrito.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;



public class Provincia {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @Column
    private String name;

    @JsonIgnore
    @OneToMany
    private List<Usuario> usuarios = new ArrayList<>();

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    public void setId(Long id) {
        this.id = id;
    }

    
}
