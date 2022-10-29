package ar.edu.undef.fie.control_de_stock.efecto;

import ar.edu.undef.fie.control_de_stock.efecto.movimiento.Movimiento;
import ar.edu.undef.fie.control_de_stock.efecto.estado.Estado;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class EstadoAbastecimiento {

    private Efecto efecto;
    private Integer cantidadnecesaria;
    private List<Movimiento> movimientos;
    private Estado estado;

    public EstadoAbastecimiento(Efecto efecto, Integer cantidadnecesaria, List<Movimiento> movimientos, Estado estado) {
        this.efecto = efecto;
        this.cantidadnecesaria = cantidadnecesaria;
        this.movimientos = new ArrayList<>();
        this.estado = estado;
    }

    public Efecto getEfecto() {return efecto;}
    public void setEfecto(Efecto efecto) {this.efecto = efecto;}

    public Integer getCantidadnecesaria() {return cantidadnecesaria;}
    public void setCantidadnecesaria(Integer cantidadnecesaria) {this.cantidadnecesaria = cantidadnecesaria;}

    public List<Movimiento> getMovimientos() {return movimientos;}
    public void setMovimientos(List<Movimiento> movimientos) {this.movimientos = movimientos;}

    public Estado getEstado() {return estado;}
    public void setEstado(Estado estado) {this.estado = estado;}

    public void imprimir() {
        System.out.println("****************");
        System.out.println("Efecto: "+ efecto.getDescripcionTipo());
        System.out.println("Cantidad: "+ efecto.getCantidad());
        estado.imprimir(this);
    }

    public void imprimirMovimientos() {
        for (Movimiento mov : this.getMovimientos()) {
            if(this.getMovimientos().size() > 0)
                mov.imprimirMovimiento();
        }
    }

    public void calcularEstado() {

        Long cantidadActual = this.getEfecto().getCantidad();

        for(Movimiento m : this.getMovimientos()) {

        }
    }

}
