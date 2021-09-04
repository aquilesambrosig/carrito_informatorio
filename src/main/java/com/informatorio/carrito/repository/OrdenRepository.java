package com.informatorio.carrito.repository;

import java.util.List;

import com.informatorio.carrito.models.Orden;
import com.informatorio.carrito.models.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenRepository extends JpaRepository<Orden, Long> {

    List<Orden> findByUsuario(Usuario user);

    

 
    
}