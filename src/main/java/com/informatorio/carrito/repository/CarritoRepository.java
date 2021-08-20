package com.informatorio.carrito.repository;


import com.informatorio.carrito.models.Carrito;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends CrudRepository<Carrito, Long>{
    
}
