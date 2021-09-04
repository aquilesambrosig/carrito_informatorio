package com.informatorio.carrito.repository;


import com.informatorio.carrito.models.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Usuario findByEmail(String email);
    List<Usuario> findByCiudadContaining(String ciudad);
    
}