package ar.edu.undef.fie.control_de_stock.efecto.movimiento;

public interface Movimiento {

    Long realizarMovimiento(Long cantidadEfecto);
    void imprimirMovimiento();
}
