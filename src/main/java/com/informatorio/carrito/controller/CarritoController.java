package com.informatorio.carrito.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Id;
import javax.validation.Valid;
import javax.websocket.server.PathParam;


import com.informatorio.carrito.models.Carrito;
import com.informatorio.carrito.models.EstadoCarrito;
import com.informatorio.carrito.models.LineaDeCarrito;
import com.informatorio.carrito.models.Producto;
import com.informatorio.carrito.models.Usuario;
import com.informatorio.carrito.repository.CarritoRepository;
import com.informatorio.carrito.repository.ProductoRepository;
import com.informatorio.carrito.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.informatorio.carrito.config.exception.BadRequestException;
import com.informatorio.carrito.config.exception.NotFoundException;
import com.informatorio.carrito.dto.OperacionCarrito;
import javassist.expr.NewArray;
import net.bytebuddy.asm.Advice.Return;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class CarritoController {

    private final UsuarioRepository usuarioRepository;
    private final CarritoRepository carritoRepository;
    private final ProductoRepository productoRepository;

    public CarritoController(UsuarioRepository usuarioRepository, CarritoRepository carritoRepository,
                             ProductoRepository productoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.carritoRepository = carritoRepository;
        this.productoRepository = productoRepository;
    }

    @GetMapping(value = "/carrito/usuario={id}")
    public ResponseEntity<List<Carrito>> verCarritosPorUsuario(@PathVariable("id") Long id){
        try  { Usuario usuario = usuarioRepository.getById(id);
        return ResponseEntity.ok(usuario.getCarritos());
        } catch (Exception e) {
            throw new NotFoundException("USUARIO NO ENCONTRADO");
         }
            
        }
        
    
    @GetMapping(value = "/carrito/{id}")
    public ResponseEntity<?> verCarritoPorID(@PathVariable("id") Long id){
        
        try  {
            Carrito carrito = carritoRepository.findById(id).get();
            return ResponseEntity.ok(carrito);
        
    } catch (Exception e) {
        throw new NotFoundException("CARRITO NO ENCONTRADO");
     }
        
    }
    
    


    

    @PostMapping(value = "/usuario/{id}/carrito")
    public Carrito crearCarrito(@PathVariable("id") Long id){
        Carrito carrito = new Carrito();
        carrito.setEstadoCarrito(EstadoCarrito.ACTIVO);
        carrito.setCart_total(new BigDecimal(0));
        Usuario usuario = usuarioRepository.getById(id);
        carrito.setUsuario(usuario);
     
        List<Carrito> carritos_usuario = usuario.getCarritos();
        carritos_usuario.add(carrito);

        if (carritos_usuario.size() >1) {
            Carrito ultimo = carritos_usuario.get(carritos_usuario.size() - 2);
            ultimo.setEstadoCarrito(EstadoCarrito.CERRADO);
        }
        else {
           
        }
     
        return carritoRepository.save(carrito);
    
}

   /* @PutMapping(value = "/carrito/{id}")
    public Carrito modificarCarrito(@PathVariable("id") Long id, @RequestBody Carrito carrito) {
        Carrito carritoExistente = carritoRepository.findById(id).get();
        carritoExistente.setEstadoCarrito(carrito.getEstadoCarrito());
        carritoExistente.setProductos(carrito.getProductos());
    
        return carritoRepository.save(carritoExistente);
    
    }*/
    @PutMapping("/usuario/{id}/carrito/{idCarrito}")
    public ResponseEntity<?> agregarProducto(@PathVariable("id") Long userId, @PathVariable("idCarrito") Long idCarrito,
                                             @Valid @RequestBody OperacionCarrito operacionCarrito) {
        Carrito carrito = carritoRepository.getById(idCarrito);
        Producto producto = productoRepository.getById(operacionCarrito.getProductoId());
        LineaDeCarrito lineaDeCarrito = new LineaDeCarrito();
        lineaDeCarrito.setCarrito(carrito);
        lineaDeCarrito.setProducto(producto);
        lineaDeCarrito.setCantidad(operacionCarrito.getCantidad());
        carrito.agregarLineDeCarrito(lineaDeCarrito);
        //////ESTOS DOS PERSISTEN EN LA BASE DE DATOS NO USARLOS ACA CON CARRITO. USARLOS CON ORDEN CUANDO GENERE LA ENTIDAD "ORDEN"
        /*lineaDeCarrito.setSubtotal(producto.getPrecioUnitario().multiply(new BigDecimal(operacionCarrito.getCantidad())));
        carrito.setCart_total(carrito.getCart_total().add(lineaDeCarrito.getSubtotal()));*/
        try {carritoRepository.save(carrito);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    
        } catch (Exception e) {
            throw new BadRequestException("CARRITO NO ENCONTRADO");
         }
    }

    @DeleteMapping(value = "/carrito/{id}")
    public String borrarCarrito(@PathVariable("id") Long id){
        carritoRepository.deleteById(id);
        return "Carrito borrado exitosamente";
    }

}
