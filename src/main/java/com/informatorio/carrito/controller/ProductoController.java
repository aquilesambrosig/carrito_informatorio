
package com.informatorio.carrito.controller;

import com.informatorio.carrito.config.exception.NotFoundException;
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
@RequestMapping(value="/producto")
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    

    @GetMapping(value = "")
    public List<Producto> verTodosLosProductos() {
        return productoRepository.findAll();
    }


    @PostMapping(value = "")
    public Producto crearProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?>   verProductoPorID(@PathVariable("id") Long id) throws NotFoundException{
        try {return ResponseEntity.ok(productoRepository.findById(id).get());
            
        } catch (Exception e) {
           throw new NotFoundException("PRODUCTO NO ENCONTRADO");
        }

        
        
        
        
    }
    @GetMapping(value = "/nombre")
    public ResponseEntity<?> buscarPorNombre(@RequestParam(value="a") String nombre) {
       try  {return ResponseEntity.ok(productoRepository.findByNombreStartingWith(nombre));
        
    } catch (Exception e) {
        throw new NotFoundException("PRODUCTO NO ENCONTRADO");
     }
    }


    @GetMapping(value = "/categoria")
    public List<Producto> findAll(@RequestParam(value="id") Long id) {
    Categoria data = categoriaRepository.findAllById(id);
    return productoRepository.findByCategorias(data);

        
    }

    //// QUEDA ESTO PARA CUANDO IMPLEMENTE CATEGORIAS*/

    @GetMapping(value = "/buscar")
    public List<Producto> buscarPorCategoriaYNombre(@RequestParam(value="categoria", required = false) Long categoria, @RequestParam(value="nombre", required = false) String nombre) {
        Categoria data = categoriaRepository.findAllById(categoria);
        return productoRepository.findByCategoriasAndNombreStartingWith(data, nombre);
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
