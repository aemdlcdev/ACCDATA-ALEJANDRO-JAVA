/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.aem.filesstreams;

import com.aem.filesstreams.controlador.ControlEscritura;
import com.aem.filesstreams.controlador.ControlLectura;
import com.aem.filesstreams.modelo.Escritura;
import com.aem.filesstreams.modelo.Lectura;
import com.aem.filesstreams.vista.InterfazVista;
import com.aem.filesstreams.vista.VistaTexto;



/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 */
public class FilesStreams {

    public static void main(String[] args) {
        InterfazVista vista = new VistaTexto();
        Lectura lector = new Lectura("");
        Escritura escritor = new Escritura("");
        ControlLectura cl = new ControlLectura(vista,lector);
        ControlEscritura ce = new ControlEscritura(vista,escritor);
        vista.arranca();
  }
}
