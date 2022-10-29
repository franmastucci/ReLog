package ar.edu.undef.fie.control_de_stock.efecto.estado;

import ar.edu.undef.fie.control_de_stock.efecto.EstadoAbastecimiento;

public interface Estado {

    void imprimir(EstadoAbastecimiento context);

    String getEstado();
}
