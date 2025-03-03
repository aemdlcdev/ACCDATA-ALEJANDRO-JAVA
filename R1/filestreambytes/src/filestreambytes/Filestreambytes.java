/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package filestreambytes;

import filestreambytes.controlador.Controlador;
import filestreambytes.modelo.Copia;
import filestreambytes.modelo.Escritura;
import filestreambytes.modelo.Lectura;
import filestreambytes.modelo.objetos.Empleado;
import filestreambytes.vista.InterfazVista;
import filestreambytes.vista.VistaTexto;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 4 oct 2024
 */
public class Filestreambytes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        Escritura modeloE = new Escritura("archivo_binario.bin");
        modeloE.escribirDatosSimples();
        Lectura modeloL = new Lectura("archivo_binario.bin");
        Copia modeloC = new Copia("C:\\Users\\b15-03m\\Pictures\\bigsur.jpg", "C:\\Users\\b15-03m\\Pictures\\bigsur_copia.jpg");
        modeloC.copiarArchivo();
        System.out.println(modeloL.lecturaDatosSimple());
        
        
        Lectura modeloLo = new Lectura("archivo_con_objetos");
        Escritura modeloEo = new Escritura("archivo_con_objetos");

        Empleado empleado = new Empleado("Fernando", "Ureña",23,800);
        modeloEo.escribirObjetos(empleado);

        empleado = new Empleado("Ana", "Garcia",30,1900);
        modeloEo.escribirObjetos(empleado);

        empleado = new Empleado("Israel", "Diaz",31,200);
        modeloEo.escribirObjetos(empleado);
        
        ArrayList<Empleado> empleados = (ArrayList<Empleado>)(ArrayList<?>) modeloLo.lecturaObjetos();

        ArrayList<Object> empleadosObejtos = modeloLo.lecturaObjetos();

        empleadosObejtos.forEach(System.out::println); // Recorro el array y voy imprimiendo los objetos
            
        */
        InterfazVista vista = new VistaTexto();
        Escritura escritor = new Escritura("");
        Lectura lector = new Lectura("");
        Controlador controlador = new Controlador(vista,escritor,lector);
        vista.arranca();
    }
}
