
package com.informatorio.carrito.controller;

import com.informatorio.carrito.models.Categoria;
import com.informatorio.carrito.models.Producto;
import com.informatorio.carrito.repository.CategoriaRepository;
import com.informatorio.carrito.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController()
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;

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
    @GetMapping(value = "/producto/buscarC")
    public Categoria buscarCategoria(@RequestParam(value="categoria") Long id) {
        return categoriaRepository.findAllById(id);
        
    }

    @GetMapping(value = "/producto/buscarN")
    public List<Producto> findAll(@RequestParam(value="categoria") Long id) {
    Categoria data = categoriaRepository.findAllById(id);
    return productoRepository.findByCategorias(data);

        
    }

    @GetMapping(value = "/categorias")
    public List<Categoria> verCategorias(){
        return categoriaRepository.findAll();
    }
    //// QUEDA ESTO PARA CUANDO IMPLEMENTE CATEGORIAS*/

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
        productoExistente.setCategorias(producto.getCategorias());
    
        return productoRepository.save(productoExistente);
    
    }
}
