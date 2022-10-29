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
import ar.edu.undef.fie.control_de_stock.requerimiento.Solicitud;

import java.util.ArrayList;
import java.util.List;

public class Organizacion {

    private  String nombre;
    private List<EstadoAbastecimiento> estados;
    private Ubicacion pocision;

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
        Estado conStock = new ConStock();

        Clase vestuarioClase = new Vestuario();
        Efecto vestuarioEfecto = new Efecto(1, "Vestuario", 150L,"kg",vestuarioClase);
        EstadoAbastecimiento vestuarioEstado = new EstadoAbastecimiento(vestuarioEfecto,50,null, conStock);

        Clase municionClase = new Municion();
        Efecto municionEfecto = new Efecto(2, "Municion", 150L,"kg",municionClase);
        EstadoAbastecimiento municionEstado = new EstadoAbastecimiento(municionEfecto, 50,null,conStock);


        Clase racionamientoClase = new Racionamiento();
        Efecto racionamientoEfecto = new Efecto(3, "Racionamiento", 150L,"kg",racionamientoClase);
        EstadoAbastecimiento racionamientoEstado = new EstadoAbastecimiento(racionamientoEfecto,50,null,conStock);


        Clase combustibleClase = new Combustible();
        Efecto combustibleEfecto = new Efecto(4, "Combustible", 150L,"l",combustibleClase);
        EstadoAbastecimiento combustibleEstado = new EstadoAbastecimiento(combustibleEfecto,50,null,conStock);

        List<EstadoAbastecimiento> lista = new ArrayList<>();
        lista.add(vestuarioEstado);
        lista.add(racionamientoEstado);
        lista.add(municionEstado);
        lista.add(combustibleEstado);

        return lista;
    }

    public void generarMovimiento(String efecto, Long cantidad, String tipo) {
        for(EstadoAbastecimiento estadoAbastecimiento : this.getEstados()) {
            if(estadoAbastecimiento.getEfecto().getDescripcionTipo().equals(efecto)) {
                Movimiento mov = null;
                if (tipo.equalsIgnoreCase("egreso")) {
                    mov = new Egreso(cantidad, "egreso");
                } else if (tipo.equalsIgnoreCase("ingreso")) {
                    mov = new Ingreso(cantidad, "ingreso");
                }
                estadoAbastecimiento.getMovimientos().add(mov);
                estadoAbastecimiento.getEfecto().setCantidad(
                        mov.realizarMovimiento(estadoAbastecimiento.getEfecto().getCantidad()));
                calcularEstado(estadoAbastecimiento);
            }
        }
    }

    private void calcularEstado(EstadoAbastecimiento estadoAbastecimiento) {
        Estado conFaltantes = new ConFaltantes();
        Estado conStock = new ConStock();

        if( estadoAbastecimiento.getEfecto().getCantidad() <= estadoAbastecimiento.getCantidadnecesaria())
            if(estadoAbastecimiento.getEstado().getEstado().contains("stock"))
                estadoAbastecimiento.setEstado(conFaltantes);

        if( estadoAbastecimiento.getEfecto().getCantidad() > estadoAbastecimiento.getCantidadnecesaria())
            if(estadoAbastecimiento.getEstado().getEstado().contains("faltantes"))
                estadoAbastecimiento.setEstado(conStock);
    }

    public void imprimirEfectos() {
        for (EstadoAbastecimiento estado : this.getEstados()) {
            estado.imprimir();
            if(estado.getMovimientos().size() > 0)
                estado.imprimirMovimientos();
        }
    }

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public List<EstadoAbastecimiento> getEstados() {return estados;}
    public void setEstados(List<EstadoAbastecimiento> estados) {this.estados = estados;}

    public Ubicacion getPocision() {return pocision;}
    public void setPocision(Ubicacion pocision) {this.pocision = pocision;}
}
