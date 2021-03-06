package com.informatorio.carrito.repository;


import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.informatorio.carrito.models.Categoria;
import com.informatorio.carrito.models.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{

    List<Producto> findByNombreStartingWith(String nombre);
    List<Producto> findByCategorias(Categoria data);
    List<Producto> findByCategoriasAndNombreStartingWith(Categoria data, String nombre);
    Producto getById(Long idProducto);
    List<Producto> findByCategoriasAndNombre(Categoria data, String nombre);
    List<Producto> getByCategoriasAndNombreStartingWith(Categoria data, String nombre);
    List<Producto> getByCategorias(Categoria data);
    List<Producto> findByFechaAltaAfter(LocalDate fechaAlta);
    List<Producto> findByFechaAltaBetween(LocalDate desde, LocalDate hasta);
    Producto findByCodigoInventario(String codigoInventario);
    
   

    

   
   

  
  
}