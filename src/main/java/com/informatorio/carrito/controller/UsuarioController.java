package com.informatorio.carrito.controller;

import java.util.List;

import com.informatorio.carrito.models.Usuario;
import com.informatorio.carrito.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping(value = "/usuario")
    public Usuario crearUsuario(@RequestBody Usuario producto){
        return usuarioRepository.save(producto);
    }

    @RequestMapping(value = "/saludo", method = RequestMethod.GET)
    public String hello() {
        return "hola informatorio";
    }

  

    @GetMapping(value = "/usuario")
    public List<Usuario> verUsuarios(){
        return usuarioRepository.findAll();
    }

    @GetMapping(value = "/usuario/{id}")
    public Usuario getUsuarioPorId(@PathVariable("id") Long id){
        return usuarioRepository.getById(id);
    }

    @DeleteMapping(value = "/usuario/{id}")
    public void borrarUsuarioPorId(@PathVariable("id") Long id){
        Usuario usuario =  usuarioRepository.getById(id);
        usuarioRepository.delete(usuario);
    }

    @PutMapping(value = "/usuario/{id}")
    public Usuario modificarUsuarioPorId(@PathVariable("id") Long id, @RequestBody Usuario usuario){
        Usuario usuarioExistente = usuarioRepository.getById(id);
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setApellido(usuario.getApellido());
        usuarioExistente.setDireccion(usuario.getDireccion());
        return usuarioRepository.save(usuarioExistente);
    }

}