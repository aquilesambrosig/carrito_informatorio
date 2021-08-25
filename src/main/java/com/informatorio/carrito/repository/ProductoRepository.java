package com.informatorio.carrito.repository;


import java.util.List;

import com.informatorio.carrito.models.Categoria;
import com.informatorio.carrito.models.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{

    List<Producto> findByNombreStartingWith(String nombre);

    

    List<Producto> findByNombreAndDescripcionStartingWith(String nombre, String descripcion);





    

  
}