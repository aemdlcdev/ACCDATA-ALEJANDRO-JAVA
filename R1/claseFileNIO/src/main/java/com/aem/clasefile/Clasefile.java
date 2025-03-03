/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.aem.clasefile;

import com.aem.clasefile.controlador.ControlArchivo;
import com.aem.clasefile.controlador.ControlCarpeta;
import com.aem.clasefile.modelo.Archivo;
import com.aem.clasefile.modelo.Carpeta;
import com.aem.clasefile.vista.CarpetaVistaTexto;
import com.aem.clasefile.vista.InterfazVista;
import com.aem.clasefile.vista.VistaGrafica;

/**
 *Clase ejemplo para mostrar el funcionamiento de la clase File
 * 
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 18 setp 2024
 * 
 */
public class Clasefile {

    public static void main(String[] args) {
        InterfazVista vista = new VistaGrafica();
        Carpeta modelo = new Carpeta();
        Archivo modeloA = new Archivo();
        ControlCarpeta controlador = new ControlCarpeta(vista, modelo);
        ControlArchivo controladorA = new ControlArchivo(vista, modeloA);
        
        vista.arranca();
    }
}
