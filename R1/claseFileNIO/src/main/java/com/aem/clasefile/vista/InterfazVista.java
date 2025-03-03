/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.aem.clasefile.vista;

import com.aem.clasefile.controlador.ControlArchivo;
import com.aem.clasefile.controlador.ControlCarpeta;
import javax.swing.JTextArea;


/**
 * Interfaz con los metodos que deben implementar las vistas que se generen
 * para la aplicacion
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 18 sept 2024
 */
public interface InterfazVista {
    
    void setControlador(ControlCarpeta c);
    void setControlador(ControlArchivo ca);
    void arranca();
    
    JTextArea getTextArea();
    
    void recorreYMuestra(String[] s);
    String getRuta();
    String getNombre();
    String getNombreNuevo();
    String getRutaNueva();
    //Constantes
    static final String CREARCARPETARUTACOMPLETA = "Crea carpeta recibiendo la ruta completa";
    static final String CREARCARPETARUTACOMPLETAPADREYNOMBRE = "Crea una carpeta recibiendo la ruta padre";
    static final String CREARCARPETACOMPLETAFILEPADREYNOMBRE = "Crea carpeta recibiendo el File y el nombre";
    static final String RECORREYMUESTRA = "Muestra el contenido de un directorio";
    static final String ELIMINAARCHIVO = "Elimina archivo o archivos de un directorio";
    static final String RENOMBRAARCHIVO = "Rrenombra un archivo";
    static final String COPIAARCHIVO = "Copia archivo en la ruta especificada";
    static final String ELIMINATODO = "Elimina los archivos y directorio de un directorio indicado";

}
