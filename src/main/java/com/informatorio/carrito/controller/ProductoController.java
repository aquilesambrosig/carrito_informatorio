
package com.informatorio.carrito.controller;

import com.informatorio.carrito.models.Producto;
import com.informatorio.carrito.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController()
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @PostMapping(value = "/producto")
    public Producto crearProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }
    @GetMapping(value = "/producto/{id}")
    public Producto verProductoPorID(@PathVariable("id") Long id){
        Producto producto = productoRepository.findById(id).get();
        return producto;
    }
    @GetMapping(value = "/producto/buscar")
    public List<Producto> buscarPorNombre(@RequestParam(value="nombre") String nombre) {
        return productoRepository.findByNombreStartingWith(nombre);
        
    }
    /*@GetMapping(value = "/producto/buscar")
    public List<Producto> buscarPorCategoria(@RequestParam(value="categoria") String categoria) {
        return productoRepository.findByCategoriaStartingWith(categoria);
        
    } QUEDA ESTO PARA CUANDO IMPLEMENTE CATEGORIAS*/ 

    /*@GetMapping(value = "/producto/buscar")
    public List<Producto> buscarPorCategoriaYNombre(@RequestParam(value="categoria") String categoria, @RequestParam(value="nombre") String nombre) {
        return productoRepository.findByNombreAndDescripcionStartingWith(categoria, nombre);
    }QUEDA ESTO PARA CUANDO IMPLEMENTE CATEGORIAS*/ 

    @GetMapping(value = "/producto")
    public List<Producto> verTodosLosProductos() {
        return productoRepository.findAll();
    }

    @DeleteMapping(value = "/producto/{id}")
    public String eliminarProductoPorID(@PathVariable("id") Long id) {
        productoRepository.deleteById(id);
        return "Producto eliminado";

    }

    @PutMapping(value = "/producto/{id}")
    public Producto modificarproducto(@PathVariable("id") Long id, @RequestBody Producto producto) {
        Producto productoExistente = productoRepository.findById(id).get();
        productoExistente.setPrecio(producto.getPrecio());
    
        return productoRepository.save(productoExistente);
    
    }
}
