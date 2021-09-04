package com.informatorio.carrito.services;

import com.informatorio.carrito.models.Carrito;
import com.informatorio.carrito.models.EstadoCarrito;
import com.informatorio.carrito.repository.CarritoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarritoService {  
    @Autowired
    private static CarritoRepository carritoRepository;
    
    public static void hacerCarritoComprado(Carrito carrito){
        carrito.setEstadoCarrito(EstadoCarrito.CERRADO);
        carritoRepository.save(carrito);
    }
    
}
