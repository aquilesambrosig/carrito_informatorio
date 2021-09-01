package com.informatorio.carrito.repository;


import com.informatorio.carrito.models.Carrito;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long>{

    

    Carrito getById(Long idCarrito);
    
}
