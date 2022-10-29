package ar.edu.undef.fie.control_de_stock.requerimiento;

import ar.edu.undef.fie.control_de_stock.efecto.Efecto;

import java.util.ArrayList;
import java.util.List;

public class Solicitud {

    private Efecto tipo;

    private Long cantidad;

    public Solicitud(Efecto tipo, Long cantidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    public Efecto getTipo() {return tipo;}
    public void setTipo(Efecto tipo) {this.tipo = tipo;}

    public Long getCantidad() {return cantidad;}
    public void setCantidad(Long cantidad) {this.cantidad = cantidad;}

}
