package ar.edu.undef.fie.control_de_stock.organizacion;

import ar.edu.undef.fie.control_de_stock.efecto.Efecto;
import ar.edu.undef.fie.control_de_stock.efecto.EstadoAbastecimiento;
import ar.edu.undef.fie.control_de_stock.efecto.clase.Clase;
import ar.edu.undef.fie.control_de_stock.efecto.clase.impl.Combustible;
import ar.edu.undef.fie.control_de_stock.efecto.clase.impl.Municion;
import ar.edu.undef.fie.control_de_stock.efecto.clase.impl.Racionamiento;
import ar.edu.undef.fie.control_de_stock.efecto.clase.impl.Vestuario;
import ar.edu.undef.fie.control_de_stock.efecto.estado.Estado;
import ar.edu.undef.fie.control_de_stock.efecto.estado.impl.ConFaltantes;
import ar.edu.undef.fie.control_de_stock.efecto.estado.impl.ConStock;
import ar.edu.undef.fie.control_de_stock.efecto.movimiento.Movimiento;
import ar.edu.undef.fie.control_de_stock.efecto.movimiento.impl.Egreso;
import ar.edu.undef.fie.control_de_stock.efecto.movimiento.impl.Ingreso;
import ar.edu.undef.fie.control_de_stock.requerimiento.Requerimiento;
import ar.edu.undef.fie.control_de_stock.requerimiento.Solicitud;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Organizacion {

    private  String nombre;
    private List<EstadoAbastecimiento> estados;
    private Ubicacion pocision;

    private static Logger logger = Logger.getLogger("log");

    private  Organizacion(String nombre, List<EstadoAbastecimiento> estados, Ubicacion pocision) {
        this.nombre = nombre;
        this.estados = estados;
        this.pocision = pocision;
    }

    public static  Organizacion altaOrganizacion(String nombre,Ubicacion pocision) {
        return new Organizacion(nombre,darEfectosDeAlta(),pocision);
    }

    public static  Organizacion altaOrganizacion(String nombre, List<EstadoAbastecimiento> estados,Ubicacion pocision) {
        return new Organizacion(nombre,estados,pocision);
    }

    public static List<EstadoAbastecimiento> darEfectosDeAlta() {
        Efecto vestuarioEfecto = new Efecto(1, "Vestuario", 150L,"kg",new Vestuario());
        EstadoAbastecimiento vestuarioEstado = new EstadoAbastecimiento(vestuarioEfecto,50,null,  new ConStock());

        Efecto municionEfecto = new Efecto(2, "Municion", 150L,"kg", new Municion());
        EstadoAbastecimiento municionEstado = new EstadoAbastecimiento(municionEfecto, 50,null, new ConStock());

        Efecto racionamientoEfecto = new Efecto(3, "Racionamiento", 150L,"kg",new Racionamiento());
        EstadoAbastecimiento racionamientoEstado = new EstadoAbastecimiento(racionamientoEfecto,50,null, new ConStock());

        Efecto combustibleEfecto = new Efecto(4, "Combustible", 150L,"l",new Combustible());
        EstadoAbastecimiento combustibleEstado = new EstadoAbastecimiento(combustibleEfecto,50,null, new ConStock());

        List<EstadoAbastecimiento> lista = new ArrayList<>();
        lista.add(vestuarioEstado);lista.add(racionamientoEstado);lista.add(municionEstado);lista.add(combustibleEstado);

        return lista;
    }


    public void generarMovimiento(String efecto, Long cantidad, String tipo) {
        this.getEstados().stream()
                .filter(abastecimiento -> abastecimiento.getEfecto().getDescripcionTipo().equalsIgnoreCase(efecto))
                .forEach(abastecimiento -> {
                    Movimiento mov = null;
                    if (tipo.equalsIgnoreCase("egreso")) {
                        mov = new Egreso(cantidad, "egreso");
                    } else if (tipo.equalsIgnoreCase("ingreso")) {
                        mov = new Ingreso(cantidad, "ingreso");
                    }
                    abastecimiento.getMovimientos().add(mov);
                    abastecimiento.getEfecto().setCantidad(mov.realizarMovimiento(abastecimiento.getEfecto().getCantidad()));
                    logger.log(Level.INFO, "Movimiento agregado");
                    calcularEstado(abastecimiento);
                });
    }

    private void calcularEstado(EstadoAbastecimiento estadoAbastecimiento) {
        if( estadoAbastecimiento.getEfecto().getCantidad() <= estadoAbastecimiento.getCantidadnecesaria())
            if(estadoAbastecimiento.getEstado().getEstado().contains("stock"))
                estadoAbastecimiento.setEstado(new ConFaltantes());
        if( estadoAbastecimiento.getEfecto().getCantidad() > estadoAbastecimiento.getCantidadnecesaria())
            if(estadoAbastecimiento.getEstado().getEstado().contains("faltantes"))
                estadoAbastecimiento.setEstado(new ConStock());
    }

    public void imprimirEfectos() {
        this.getEstados().stream()
                .forEach(abastecimiento -> {
                    abastecimiento.imprimir();
                    if(abastecimiento.getMovimientos().size() > 0)
                        abastecimiento.imprimirMovimientos();
                });
    }

    public void agregarRequerimiento(Requerimiento req ){
        if(req.getConfirmacion().equals(true) && req.getOrganizacion().getNombre().equals(this.getNombre())) {
            req.getSolicitudes().stream()
                    .forEach(solicitud -> {
                        this.getEstados().stream()
                                .filter(abastecimiento -> abastecimiento.getEfecto().getTipo().equals(solicitud.getTipo().getTipo()))
                                .forEach(abastecimiento -> {
                                    this.generarMovimiento(solicitud.getTipo().getDescripcionTipo(), solicitud.getTipo().getCantidad(),"ingreso");
                                    abastecimiento.getEfecto().setCantidad(abastecimiento.getEfecto().getCantidad()+ solicitud.getTipo().getCantidad());
                                });
                    });

            logger.log(Level.INFO, "Requerimiento agregado");
        }
    }

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public List<EstadoAbastecimiento> getEstados() {return estados;}
    public void setEstados(List<EstadoAbastecimiento> estados) {this.estados = estados;}

    public Ubicacion getPocision() {return pocision;}
    public void setPocision(Ubicacion pocision) {this.pocision = pocision;}
}
