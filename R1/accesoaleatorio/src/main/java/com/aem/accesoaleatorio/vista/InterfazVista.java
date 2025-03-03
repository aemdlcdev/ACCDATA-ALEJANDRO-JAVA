/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aem.accesoaleatorio.vista;

import com.aem.accesoaleatorio.controlador.Controlador;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 */
public interface InterfazVista {
    
    // Constantes lectura
    String LECTURA_EMPLEADO="Lee un empleado pasandole un identificador";
    String MUESTRA_REGISTROS="Muestras todos los registros de un archivo";
    
    // Constantes escritura 
    String ESCRIBIR_EMPLEADO_FINAL_ARCHIVO="Escribe un empleado al final del archivo";
    String ESCRIBIR_EMPLEADO_POR_IDENTIFICADOR="Escribe un empleado por identificador";
    String BORRA_REGISTRO="Borra un registro pasandole un identificador";
    String MODIFICA_APELLIDO="Modifica el apellido de un registro pasandole un identificador";
    
    // Métodos void
    void setrControlador(Controlador c);
    void arranca();
    
    // Métodos
    String getRuta();
    String getApellido();
    int getIdentificador();
    int getDepartamento();
    long getIdentificadorL();
    double getSalario();
}
