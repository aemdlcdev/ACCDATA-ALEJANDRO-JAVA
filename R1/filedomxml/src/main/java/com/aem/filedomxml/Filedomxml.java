/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.aem.filedomxml;

import com.aem.filedomxml.controlador.Controlador;
import com.aem.filedomxml.controlador.ControladorMemoria;
import com.aem.filedomxml.modelo.Empleado;
import com.aem.filedomxml.modelo.EmpleadoLector;
import com.aem.filedomxml.modelo.GestionContenidoDom;
import com.aem.filedomxml.vista.InterfazVista;
import com.aem.filedomxml.vista.VistaTexto;
import java.util.List;
import org.w3c.dom.Element;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 */

public class Filedomxml {

    public static void main(String[] args) {
       GestionContenidoDom modelo = new GestionContenidoDom("Empleados");
       Element elem = modelo.addNodo("Empleado");
       modelo.addNodoYTexto("identificador","cargo", "salario", "1", 200050, elem);
       //modelo.mostrarPantalla("yes");
       elem = modelo.addNodo("Empleado");
       modelo.addNodoYTexto("identificador","cargo","salario","2",1500.8, elem);
        
       elem = modelo.addNodo("Empleado");
       modelo.addNodoYTexto("identificador","cargo","salario","6",1200.2, elem);
        
       modelo.generarArchivoDelDOM("empleadosP.xml", "yes");
       
       //modelo.eliminarElemento(elem, "Empleado");
       modelo.mostrarPantalla("yes");
        
       modelo.modificarSalarioEmpleado("6", 100.3);
       System.out.println("");
       modelo.mostrarPantalla("yes");
       
       
//        String archivoBinario = "archivo_empleados.dat";
//        String archivoXml = "empleados.xml";
//
//        EmpleadoLector empleadoReader = new EmpleadoLector();
//        List<Empleado> empleados = empleadoReader.leerEmpleados(archivoBinario);
//
//        GestionContenidoDom gestionContenidoDom = new GestionContenidoDom("Empleados");
//        for (Empleado empleado : empleados) {
//            gestionContenidoDom.agregarEmpleado(empleado);
//        }
//
//        gestionContenidoDom.generarArchivoDelDOM(archivoXml, "yes");
    }
    

        //modelo.generarArchivoDelDOM("./resources/empleados.xml");
        
//        modelo.cargarArchivoEnMemoria("./resources/empleados.xml");
        //modelo.mostrarPantalla();
        //System.out.println(modelo.getElementPrincipal());
        
        //modelo.generarArchivoDelDOM("./resources/empleados2.xml");
        
//        List<Empleado> empleList = modelo.getEmpleados();
//        for(Empleado e : empleList){
//            System.out.println(e);
//        }

//        InterfazVista vista = new VistaTexto();
//        GestionContenidoDom modelo = new GestionContenidoDom(" ");
//        
//        Controlador controlador = new Controlador(vista,modelo);
//        ControladorMemoria controladorMemoria = new ControladorMemoria(vista,modelo);
//        vista.arranca();
    
}
