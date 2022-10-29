package ar.edu.undef.fie.control_de_stock.efecto.estado.impl;

import ar.edu.undef.fie.control_de_stock.efecto.EstadoAbastecimiento;
import ar.edu.undef.fie.control_de_stock.efecto.estado.Estado;

public class ConFaltantes implements Estado {

    final String CON_FALTANTES = "con faltantes";

    @Override
    public void imprimir(EstadoAbastecimiento context) {
        System.out.println("Estado: " + CON_FALTANTES);
    }

    @Override
    public String getEstado() {return CON_FALTANTES;}
}
