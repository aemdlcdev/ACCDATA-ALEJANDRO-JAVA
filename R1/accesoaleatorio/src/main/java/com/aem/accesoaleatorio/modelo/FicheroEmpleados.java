/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.accesoaleatorio.modelo;

import java.io.File;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 7 oct 2024
 *
 */
public class FicheroEmpleados {
    
    // Tamaño de datos en bytes   
    private final int LONGITUD_LONG = 8;
    private final int LONGITUD_DOUBLE = 8;
    private final int LONGITUD_INT = 4;
    private final int LONGITUD_CHAR =2;
    
    // Tamaño fijado para la cadena de caracteres
    private final int CARACTERES_APELLIDO = 10;
    
    // Tamaño de cada uno de los campos que forman el registro del empleado
    private final int LONGITUD_IDENTIFICADOR = LONGITUD_LONG;
    private final int LONGITUD_APELLIDO = CARACTERES_APELLIDO * LONGITUD_CHAR;     
    private final int LONGITUD_DEPARTAMENTO = LONGITUD_INT;
    private final int LONGITUD_SALARIO = LONGITUD_DOUBLE;
    
    // Tamaño total del registro
    private final int LONGITUD_TOTAL = LONGITUD_IDENTIFICADOR + LONGITUD_APELLIDO + LONGITUD_DEPARTAMENTO + LONGITUD_SALARIO; 
    
    private File ruta;
    
    public FicheroEmpleados(String ruta){
        this.ruta = new File(ruta);
    }
    
    public int getCARACTERES_APELLIDO() {
        return CARACTERES_APELLIDO;
    }
    
    public int getLONGITUD_APELLIDO() {
        return LONGITUD_APELLIDO;
    }

    public int getLONGITUD_TOTAL() {
        return LONGITUD_TOTAL;
    }

    public File getRuta() {
        return ruta;
    }

    public int getLONGITUD_IDENTIFICADOR() {
        return LONGITUD_IDENTIFICADOR;
    }
    
    

    public void setRuta(String ruta) {
        this.ruta = new File(ruta);
    }
   
}
