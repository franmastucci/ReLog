package ar.edu.undef.fie.control_de_stock;

import ar.edu.undef.fie.control_de_stock.efecto.Efecto;
import ar.edu.undef.fie.control_de_stock.efecto.clase.Clase;
import ar.edu.undef.fie.control_de_stock.efecto.clase.impl.Combustible;
import ar.edu.undef.fie.control_de_stock.efecto.clase.impl.Municion;
import ar.edu.undef.fie.control_de_stock.efecto.clase.impl.Racionamiento;
import ar.edu.undef.fie.control_de_stock.efecto.clase.impl.Vestuario;
import ar.edu.undef.fie.control_de_stock.organizacion.Organizacion;
import ar.edu.undef.fie.control_de_stock.requerimiento.Requerimiento;
import ar.edu.undef.fie.control_de_stock.requerimiento.Solicitud;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Organizacion org = Organizacion.altaOrganizacion("ong", null);

        org.generarMovimiento("Vestuario",120L, "egreso");
        org.generarMovimiento("Vestuario",15L, "ingreso");
        org.generarMovimiento("Combustible",15L, "egreso");
        org.generarMovimiento("Municion",15L, "egreso");

        org.imprimirEfectos();

        Requerimiento req = Requerimiento.crearRequerimiento(org, LocalDateTime.now().plusDays(15));
        req.cargarSolicitud(1l,150L);
        //req.cargarSolicitud(3L,150L);
        req.confirmarRequerimiento();
        System.out.println(req.getConfirmacion());


    }

}