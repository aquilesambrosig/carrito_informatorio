package com.informatorio.carrito.controller;



import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Id;
import javax.websocket.server.PathParam;

import com.informatorio.carrito.config.exception.NotFoundException;
import com.informatorio.carrito.models.Carrito;
import com.informatorio.carrito.models.Categoria;
import com.informatorio.carrito.models.EstadoCarrito;
import com.informatorio.carrito.models.Usuario;
import com.informatorio.carrito.repository.CarritoRepository;
import com.informatorio.carrito.repository.CategoriaRepository;
import com.informatorio.carrito.repository.ProductoRepository;
import com.informatorio.carrito.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value="/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    
    @GetMapping(value = "")
    public ResponseEntity<?> buscarCategoria(@RequestParam(required = false, value="id") String id) {
       try { if (id == null) {
            return ResponseEntity.ok(categoriaRepository.findAll());
            
        }
        return ResponseEntity.ok(categoriaRepository.findAllById(Long.valueOf(id)));
        
    } catch (Exception e) {
        throw new NotFoundException("USUARIO NO ENCONTRADO");
     }

/*    @GetMapping(value = "")
    public List<Categoria> verCategorias(){
        return categoriaRepository.findAll();
    }
    */
}}
