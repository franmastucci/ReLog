package ar.edu.undef.fie.control_de_stock.efecto;

import ar.edu.undef.fie.control_de_stock.efecto.clase.Clase;

public class Efecto {
    private Integer tipo;  //puede ser 1,2 0 3
    private String DescripcionTipo; //puede ser "Racionamiento", "vestuario y equipo" o "Combustible y lubricante" /desayuno nombre de la clase
    private Long cantidad; //cantidad de unidades de la unidad de medida
    private String unidadMedida;  //puede ser "litros", "kilos" o "unidades"
    private Clase clase;

    public Efecto(Integer tipo, String descripcionTipo, Long cantidad, String unidadMedida, Clase clase) {
        this.tipo = tipo;
        DescripcionTipo = descripcionTipo;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
        this.clase = clase;
    }

    public Integer getTipo() {
        return tipo;
    }
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getDescripcionTipo() {
        return DescripcionTipo;
    }
    public void setDescripcionTipo(String descripcionTipo) {
        DescripcionTipo = descripcionTipo;
    }

    public Long getCantidad() {
        return cantidad;
    }
    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }
    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
}
