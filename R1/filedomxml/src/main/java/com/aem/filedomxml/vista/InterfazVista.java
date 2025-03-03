/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.aem.filedomxml.vista;

import com.aem.filedomxml.controlador.Controlador;
import com.aem.filedomxml.controlador.ControladorMemoria;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 17 oct 2024
 */
public interface InterfazVista {

    // Constantes
    
    String AÑADIRNODO="Añade un nodo raiz";
    String AÑADIRNODOHIJO="Añade un nodo hijo al nodo raiz";
    String AÑADIRNODOHIJOCONTEXTO="Añade un nodo hijo con texto dentro";
    String MOSTRARXML="Muestra un documento XML";
    String CARGARARCHIVOENMEMORIA="Carga un documento XML de disco a la memoria";
    String OBTENERELEMENTOPRINCIPAL="Obtiene el elemento raiz de un archivo XML";
    String OBTENERVALORDEUNELEMENTO="Obtiene el valor de un elemento dado";
    String OBTENEREMPLEADO="Obtiene un empleado del documento XML";
    String OBTENERLISTAEMPLEADOS="Obtiene una lista de empleados del documento XML";
    
    // Metodos void
    
    void setControlador(Controlador c);
    void setControladorMemoria(ControladorMemoria c);
    void arranca();
    
    // Metodos
    
    String getNombreDocumento();
    String getNombreNodo();
    
    
}
