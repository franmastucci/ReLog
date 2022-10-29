package ar.edu.undef.fie.control_de_stock.efecto.estado.impl;

import ar.edu.undef.fie.control_de_stock.efecto.EstadoAbastecimiento;
import ar.edu.undef.fie.control_de_stock.efecto.estado.Estado;

import java.sql.SQLOutput;

public class ConStock implements Estado {

    final String CON_STOCK = "con stock";

    @Override
    public void imprimir(EstadoAbastecimiento context) {
        System.out.println("Estado: " + CON_STOCK);
    }

    @Override
    public String getEstado() {return CON_STOCK;}
}
