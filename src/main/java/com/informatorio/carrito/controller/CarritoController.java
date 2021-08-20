package com.informatorio.carrito.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Id;
import javax.websocket.server.PathParam;

import com.informatorio.carrito.models.Carrito;
import com.informatorio.carrito.models.EstadoCarrito;
import com.informatorio.carrito.models.Usuario;
import com.informatorio.carrito.repository.CarritoRepository;
import com.informatorio.carrito.repository.ProductoRepository;
import com.informatorio.carrito.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javassist.expr.NewArray;
import net.bytebuddy.asm.Advice.Return;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class CarritoController {
    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping(value = "/carrito/{id}")
    public List<Carrito> verCarritosPorUsuario(@PathVariable("id") Long id){
        Usuario usuario = usuarioRepository.getById(id);

        return usuario.getCarritos();
        
    }

    @PostMapping(value = "/carrito/{id}")
    public Carrito crearCarrito(@PathVariable("id") Long id){
        Carrito carrito = new Carrito();
        carrito.setEstadoCarrito(EstadoCarrito.ACTIVO);
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
}
