package com.informatorio.carrito.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.informatorio.carrito.services.CarritoService;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Orden {

    /*
        - usuario
        - linea
        - carrito_id
        - fecha_creacion
        - Tipo
        - numero
        - estado (multivaluado, CONFIRMADA y CANCELADA)
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    @Column(unique = true,nullable = false)
    private Long nroFactura;

    @OneToMany(mappedBy = "orden",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineaDeCarrito> lineasDeOrden = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private Long carrito_id;

    private BigDecimal orden_total;

    public BigDecimal getOrden_total() {
        return orden_total;
    }
    public void setOrden_total(BigDecimal orden_total) {
        this.orden_total = orden_total;
    }

    @OneToMany(mappedBy = "orden",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Linea> linea = new ArrayList<>();

    /*  @CreationTimestamp
    private LocalDate fecha_creacion;


    @Column(unique = true,nullable = false)
    private Long numero;

  @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Estado estado;
*/


    @Column(nullable = true, length=200)
    private String observacion;

    public Long getCarrito_id() {
        return carrito_id;
    }
    public Long getId() {
        return id;
    }
    public List<LineaDeCarrito> getLineasDeOrden() {
        return lineasDeOrden;
    }
    public String getObservacion() {
        return observacion;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setCarrito_id(Long carrito_id) {
        this.carrito_id = carrito_id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setLineasDeOrden(List<LineaDeCarrito> lineasDeOrden) {
        this.lineasDeOrden = lineasDeOrden;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    public Long getNroFactura() {
        return nroFactura;
    }
    public void setNroFactura(Long nroFactura) {
        this.nroFactura = nroFactura;
    }
    public List<Linea> getLinea() {
        return linea;
    }
    public void setLinea(List<Linea> linea) {
        this.linea = linea;
    }

    public void agregarLinea(Linea l) {
        linea.add(l);
        l.setOrden(this);
    }

    public void removerLinea(Linea l) {
        linea.remove(l);
        l.setOrden(null);
    }

}