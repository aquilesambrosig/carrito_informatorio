package com.informatorio.carrito.repository;

import java.time.LocalDate;
import java.util.List;

import com.informatorio.carrito.models.Orden;
import com.informatorio.carrito.models.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenRepository extends JpaRepository<Orden, Long> {

    List<Orden> findByUsuario(Usuario user);
    List<Orden> findByFechaCreacion(LocalDate fechaCreacion);
    List<Orden> findByFechaCreacionAfter(LocalDate fechaCreacion);

    

 
    
}