package ar.edu.undef.fie.control_de_stock.requerimiento;

import ar.edu.undef.fie.control_de_stock.efecto.Efecto;
import ar.edu.undef.fie.control_de_stock.efecto.EstadoAbastecimiento;
import ar.edu.undef.fie.control_de_stock.efecto.clase.Clase;
import ar.edu.undef.fie.control_de_stock.efecto.clase.impl.Combustible;
import ar.edu.undef.fie.control_de_stock.efecto.clase.impl.Municion;
import ar.edu.undef.fie.control_de_stock.efecto.clase.impl.Racionamiento;
import ar.edu.undef.fie.control_de_stock.efecto.clase.impl.Vestuario;
import ar.edu.undef.fie.control_de_stock.organizacion.Organizacion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Requerimiento {

    private Organizacion organizacion;
    private LocalDateTime fechaRequeridaEntrega;
    private List<Solicitud> solicitudes;
    private Boolean confirmacion = false;

    private  Requerimiento(Organizacion organizacion, LocalDateTime fechaRequeridaEntrega) {
        this.organizacion = organizacion;
        this.fechaRequeridaEntrega = fechaRequeridaEntrega;
        this.solicitudes = new ArrayList<>();
    }

    public static Requerimiento crearRequerimiento(Organizacion org, LocalDateTime date) {
        return new Requerimiento(org,date);
    }

    public void cargarSolicitud(Long tipo, Long cantidad) {
        this.getSolicitudes().add(this.crearSolicitud(tipo, cantidad));
    }

    private Solicitud crearSolicitud(Long tipo, Long cantidad) {
        if (tipo == 1L) {
            return new Solicitud(new Efecto(1, "Vestuario", cantidad, "kg", new Vestuario()), cantidad);
        } else if (tipo == 2L) {
            return new Solicitud(new Efecto(2, "Municion", cantidad, "kg", new Municion()), cantidad);
        } else if (tipo == 3L) {
            return new Solicitud(new Efecto(3, "Racionamiento", cantidad, "kg", new Racionamiento()), cantidad);
        } else if (tipo == 4L) {
            return  new Solicitud( new Efecto(4, "Combustible", cantidad, "kg", new Combustible()), cantidad);
        } else {
            System.err.println("ERROR no se encontró el efecto");
        }
        return null;
    }

    public void cargarSolicitudes(List<Solicitud> solicitudes) {
        this.getSolicitudes().stream()
                .forEach(solicitud -> {
                    this.getSolicitudes().add(solicitud);
                });
    }

    public void confirmarRequerimiento() {
        if(!this.getSolicitudes().isEmpty()) {
            confirmacion = true;
            organizacion.getEstados().stream()
                    .forEach(abastecimiento -> {
                        this.getSolicitudes().stream()
                                .filter(solicitud -> abastecimiento.getEfecto().getTipo().equals(solicitud.getTipo().getTipo()))
                                .filter(solicitud -> abastecimiento.getCantidadnecesaria() < abastecimiento.getEfecto().getCantidad())
                                .forEach(solicitud -> {
                                    System.err.println("No se puede confirmar el requerimiento, la clase " + abastecimiento.getEfecto().getDescripcionTipo() + " no tiene faltantes");
                                    confirmacion = false;
                                } );
                    } );
        } else {
            System.err.println("el requerimiento no se puede confirmar, no hay solicitudes asociadas");
        }
    }

    public static List<Solicitud> generarSolicidtudArrayList() {
        return new ArrayList<Solicitud>();
    }

    public Organizacion getOrganizacion() {return organizacion;}
    public void setOrganizacion(Organizacion organizacion) {this.organizacion = organizacion;}

    public LocalDateTime getFechaRequeridaEntrega() {return fechaRequeridaEntrega;}
    public void setFechaRequeridaEntrega(LocalDateTime fechaRequeridaEntrega) {this.fechaRequeridaEntrega = fechaRequeridaEntrega;}

    public List<Solicitud> getSolicitudes() {return solicitudes;}
    public void setSolicitudes(List<Solicitud> solicitudes) {this.solicitudes = solicitudes;}

    public Boolean getConfirmacion() {return confirmacion;}

}