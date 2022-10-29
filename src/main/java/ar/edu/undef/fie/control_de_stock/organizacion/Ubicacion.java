package ar.edu.undef.fie.control_de_stock.organizacion;

import java.math.BigDecimal;

public class Ubicacion {

    private BigDecimal latitud;

    private BigDecimal longitud;

    public Ubicacion(BigDecimal latitud, BigDecimal longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public BigDecimal getLatitud() {return latitud;}
    public void setLatitud(BigDecimal latitud) {this.latitud = latitud;}

    public BigDecimal getLongitud() {return longitud;}
    public void setLongitud(BigDecimal longitud) {this.longitud = longitud;}
}
