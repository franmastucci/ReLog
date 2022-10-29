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
            Clase vestuarioClase = new Vestuario();
            Efecto vestuarioEfecto = new Efecto(1, "Vestuario", cantidad, "kg", vestuarioClase);
            return new Solicitud(vestuarioEfecto, vestuarioEfecto.getCantidad());
        } else if (tipo == 2L) {
            Clase municionClase = new Municion();
            Efecto municionEfecto = new Efecto(2, "Municion", cantidad, "kg", municionClase);
            return new Solicitud(municionEfecto, municionEfecto.getCantidad());
        } else if (tipo == 3L) {
            Clase racionamientoClase = new Racionamiento();
            Efecto racionamientoEfecto = new Efecto(3, "Racionamiento", cantidad, "kg", racionamientoClase);
            return new Solicitud(racionamientoEfecto, racionamientoEfecto.getCantidad());
        } else if (tipo == 4L) {
            Clase combustibleClase = new Combustible();
            Efecto combustibleEfecto = new Efecto(4, "Combustible", cantidad, "kg", combustibleClase);
            return  new Solicitud(combustibleEfecto, combustibleEfecto.getCantidad());
        } else {
            System.err.println("ERROR no se encontró el efecto");
        }
        return null;
    }

    public void cargarSolicitudes(List<Solicitud> solicitudes) {
        for(Solicitud s : this.getSolicitudes()) {
            this.getSolicitudes().add(s);
        }
    }

    public void confirmarRequerimiento() {
        if(!this.getSolicitudes().isEmpty()) {
            confirmacion = true;
            for (EstadoAbastecimiento a : organizacion.getEstados()) {
                for(Solicitud s : this.getSolicitudes()) {
                    if(a.getEfecto().getTipo().equals(s.getTipo().getTipo())) {
                        if(a.getCantidadnecesaria() < a.getEfecto().getCantidad()) {
                            System.err.println("No se puede confirmar el requerimiento, la clase "
                                    + a.getEfecto().getDescripcionTipo() + " no tiene faltantes");
                            confirmacion = false;
                            break;
                        }
                    }
                }
            }
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