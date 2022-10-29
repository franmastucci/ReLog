package ar.edu.undef.fie.control_de_stock.efecto.movimiento.impl;

import ar.edu.undef.fie.control_de_stock.efecto.movimiento.Movimiento;

public class Egreso implements Movimiento {

    private Long cantidad;

    private String descripcion;

    public Egreso(Long cantidad, String descripcion) {
        this.cantidad = cantidad;
        this.descripcion = descripcion;
    }

    @Override
    public Long realizarMovimiento(Long cantidadEfecto) {
        return cantidadEfecto - (Long) cantidad;
    }

    @Override
    public void imprimirMovimiento() {
        System.out.println("Movimiento: " + descripcion + ", Cantidad: " + cantidad);
    }

    public Long getCantidad() {return cantidad;}
    public void setCantidad(Long cantidad) {this.cantidad = cantidad;}

    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
}
