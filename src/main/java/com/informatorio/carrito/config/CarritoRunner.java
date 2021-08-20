package com.informatorio.carrito.config;

import com.informatorio.carrito.repository.CarritoRepository;
import com.informatorio.carrito.repository.ProductoRepository;
import com.informatorio.carrito.repository.UsuarioRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class CarritoRunner implements CommandLineRunner {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hola");

    
}
}
