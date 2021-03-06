package com.informatorio.carrito.controller;


import com.informatorio.carrito.config.exception.BadRequestException;
import com.informatorio.carrito.config.exception.NotFoundException;
import com.informatorio.carrito.models.*;
import com.informatorio.carrito.repository.CarritoRepository;
import com.informatorio.carrito.repository.OrdenRepository;
import com.informatorio.carrito.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.informatorio.carrito.services.CarritoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*mport static com.informatorio.carrito.models.Estado.Cancelada;
import static com.informatorio.carrito.models.Estado.Confirmada;
import static com.informatorio.carrito.models.Rol.Comerciante;
import static com.informatorio.carrito.service.OrdenService.tratarCancelarOrden;
import static com.informatorio.carrito.service.OrdenService.tratarCreacionOrden;*/
import static java.lang.Math.random;

import java.time.LocalDate;

@RestController
public class OrdenController {

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public static Long generarNumeroFactura(Carrito carrito) {
        Random random = new Random();
        Long numero = carrito.getUsuario().getId()*carrito.getId()*random.nextInt();
        return numero;
    }

        public static void cargarLineas(Carrito carrito, Orden orden){
        
        List<LineaDeCarrito> lineaDeCarritos = carrito.getLineasDeCarrito();
        for  (LineaDeCarrito d : lineaDeCarritos) {
            Linea l =  new Linea();
            orden.agregarLinea(l);
            l.setProducto_id(d.getProducto().getId());
            l.setCantidad(d.getCantidad());
            l.setPrecio(d.getProducto().getPrecioUnitario());
            l.setOrden(orden);
            
        }
       
    }
    



    @GetMapping(value = "/orden")
    public ResponseEntity<List<Orden>> verOrdenes(){
        try {return  ResponseEntity.ok(ordenRepository.findAll());
        }
        catch (Exception e) {
            throw new NotFoundException("ORDEN  NO ENONTRADO");
         }
    }

    @GetMapping(value = "/orden/{id_orden}")
    public ResponseEntity<Orden> verOrden(@PathVariable("id_orden") Long id_orden){
        try {
            return ResponseEntity.ok(ordenRepository.findById(id_orden).get());
        }
        catch (Exception e) {
            throw new NotFoundException("ORDEN  NO ENONTRADO");
         }
    }


    @GetMapping(value = "/orden/usuario")
    public ResponseEntity<List<Orden>> verOrdenPorUsuario(@RequestParam("id") Long id){
        
        try  
        { Usuario usuario = usuarioRepository.getById(id);
        return ResponseEntity.ok(usuario.getOrdenes());
        } catch (Exception e) {
            throw new NotFoundException("USUARIO NO ENCONTRADO");
            
         }
    
         
            
        }



    @PostMapping(value = "/orden/{id_carrito}/usuario/{id_usuario}")
    public ResponseEntity<?> crearOrden(@PathVariable("id_carrito") Long id_carrito, @PathVariable("id_usuario") Long id_usuario/*,@RequestBody Orden orden*/){
       // Orden orden = ordenRepository.getById(id_carrito);
        Carrito carrito = carritoRepository.getById(id_carrito);
        Orden orden = new Orden();
        if (carrito.getUsuario().getId() == id_usuario ) {
            
        

        if (
            (carrito.getEstadoCarrito()==EstadoCarrito.ACTIVO) && (carrito.getLineasDeCarrito().size()>=1)) {
            orden.setCarrito_id(id_carrito);
            //orden.setEstado(Confirmada);
            orden.setUsuario(carrito.getUsuario());
         
            orden.setObservacion("OBS");
            orden.setNroFactura(generarNumeroFactura(carrito));
            cargarLineas(carrito,orden);
            
            ////////CERRAR CARRITO AL COMPRAR///////////////
            carrito.setEstadoCarrito(EstadoCarrito.CERRADO);
            carritoRepository.save(carrito);
            ////////////////////////////////////////////////
            orden.setOrden_total(carrito.getCart_total());
            ordenRepository.save(orden);
            return new ResponseEntity<>("Orden Generada", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("CARRITO CERRADO O SIN PRODUCTOS", HttpStatus.I_AM_A_TEAPOT);
    }
    return new ResponseEntity<>("NO ESTAS AUTORIZADO", HttpStatus.UNAUTHORIZED);
    }
       
    
/*
    @PutMapping(value = "usuario/{id_usuario}/orden/{id_carrito}/close")
    public Orden cancelarOrden(@PathVariable("id_carrito") Long id_carrito, @PathVariable("id_usuario") Long id_usuario){
       /* //Orden orden = ordenRepository.getById(id_carrito);
        Carrito carrito = carritoRepository.getById(id_carrito);

        if ((carrito.getEstadoCarrito()==EstadoCarrito.ACTIVO) && (carrito.getLineasDeCarrito().size()>=1)) {
            orden.setCarrito_id(id_carrito);
            //orden.setEstado(Confirmada);
            orden.setUsuario(carrito.getUsuario());
            orden.setObservacion(orden.getObservacion());
            orden.setNroFactura(generarNumeroFactura(carrito));
            cargarLineas(carrito,orden);
            ////////CERRAR CARRITO AL COMPRAR///////////////
            carrito.setEstadoCarrito(EstadoCarrito.CERRADO);
            carritoRepository.save(carrito);
            ////////////////////////////////////////////////
            return ordenRepository.save(orden);
        }
        return null;
    }
*/

    @GetMapping(value = "/orden/fecha/{fecha}")
    //public ResponseEntity getOrdenFecha(@PathVariable("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDeCreacion)
    public ResponseEntity getOrdenFecha(@PathVariable("fecha") String fecha) {

        try{
           LocalDate fechaDeCreacion = LocalDate.parse(fecha);
    
    return ResponseEntity.ok(ordenRepository.findByFechaCreacionAfter(fechaDeCreacion));
    } catch (Exception e) {
        throw new BadRequestException("MAL FORMATO FECHA");
    }
}   
    
    @GetMapping(value = "/orden/fecha")
    //public ResponseEntity getOrdenFecha(@PathVariable("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDeCreacion)
    public ResponseEntity getOrdenFechaEntre(@RequestParam(value="desde", required = false, defaultValue = "1900-01-01") String fechaDesde
    ,@RequestParam(value="hasta", required = false, defaultValue = "2100-12-12") String fechaHasta) {

        try{
            LocalDate desde = LocalDate.parse(fechaDesde);
            LocalDate hasta = LocalDate.parse(fechaHasta);

        return ResponseEntity.ok(ordenRepository.findByFechaCreacionBetween(desde, hasta));
            } catch (Exception e) {
        throw new BadRequestException("MAL FORMATO FECHA");
            }
        } 

    




    @DeleteMapping(value = "/orden/{id_orden}")
    public ResponseEntity<?> borrarOrden(@PathVariable("id_orden") Long id_orden){
        try {
            Orden orden =  ordenRepository.findById(id_orden).get();
        ordenRepository.delete(orden);
        return new ResponseEntity<>("ORDEN Borrada", HttpStatus.OK);
    }
    catch (Exception e) {
        throw new NotFoundException("ORDEN  NO ENONTRADO");
     }
    }

    @GetMapping(value = "/usuario/{id_usuario}/orden")
    public ResponseEntity<List<Orden>> obtenerOrdenesDelUsuario(@PathVariable("id_usuario") Long id_usuario){
        try {Usuario user = usuarioRepository.findById(id_usuario).get();
        return ResponseEntity.ok(ordenRepository.findByUsuario(user));
    }
    catch (Exception e) {
        throw new NotFoundException("ORDEN  NO ENONTRADO");
     }
    }

}