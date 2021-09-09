package com.informatorio.carrito.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.informatorio.carrito.config.exception.BadRequestException;
import com.informatorio.carrito.models.Usuario;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping(value = "/usuario")
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody Usuario user){
        if (usuarioRepository.findByEmail(user.getEmail()) != null) {
            return new ResponseEntity<> ("Existe ese correo",HttpStatus.CONFLICT);
        }
        usuarioRepository.save(user);
        return new ResponseEntity<>("Usuario creado", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/saludo", method = RequestMethod.GET)
    public String hello() {
        return "hola informatorio";
    }




    @GetMapping(value = "/usuario")
    public ResponseEntity<?> verUsuarios(@RequestParam(value="ciudad", required = false) String ciudad, 
    @RequestParam(value="id", required = false) Long id)
    {
        if (ciudad != null) {
            
            return ResponseEntity.ok(usuarioRepository.findByCiudadContaining(ciudad));
            
        } else if (id != null) {
           return ResponseEntity.ok(usuarioRepository.findById(id).get());

           
        }
        return ResponseEntity.ok(usuarioRepository.findAll());

       
    }




    @GetMapping(value = "/usuario/fecha")
    //public ResponseEntity getUsuarioFecha(@PathVariable("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDeCreacion)
    public ResponseEntity getUsuarioFechaEntre(@RequestParam(value="desde", required = false, defaultValue = "1900-01-01") String fechaDesde
    ,@RequestParam(value="hasta", required = false, defaultValue = "2100-12-12") String fechaHasta) {

        try{
           LocalDate desde = LocalDate.parse(fechaDesde);
           LocalDate hasta = LocalDate.parse(fechaHasta);
    
    return ResponseEntity.ok(usuarioRepository.findByFechaAltaBetween(desde, hasta));
    } catch (Exception e) {
        throw new BadRequestException("MAL FORMATO FECHA");
        }
    } 



    @GetMapping(value = "/usuario/{id}")
    public Usuario getUsuarioPorId(@PathVariable("id") Long id){
        return usuarioRepository.findById(id).get();
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