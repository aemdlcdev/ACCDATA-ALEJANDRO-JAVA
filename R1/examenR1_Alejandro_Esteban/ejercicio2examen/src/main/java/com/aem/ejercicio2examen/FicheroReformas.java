/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.ejercicio2examen;

import java.io.File;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 13 nov 2024
 *
 */
public class FicheroReformas {
    
    // Tamaño de datos en bytes   
    private final int LONGITUD_LONG = 8;
    private final int LONGITUD_DOUBLE = 8;
    private final int LONGITUD_CHAR =2;
    
    // Tamaño fijado para la cadena de caracteres
    private final int CARACTERES_DESCRIPCION = 25;
    
    private final int CARACTERES_DIRECCION = 30;
    
    // Tamaño de cada uno de los campos que forman el registro del empleado
    private final int LONGITUD_IDENTIFICADOR = LONGITUD_LONG;
    private final int LONGITUD_DESCRIPCION = CARACTERES_DESCRIPCION * LONGITUD_CHAR;     
    private final int LONGITUD_DIRECCION = CARACTERES_DIRECCION * LONGITUD_CHAR;   
    private final int LONGITUD_SALARIO = LONGITUD_DOUBLE;
    
    // Tamaño total del registro
    private final int LONGITUD_TOTAL = LONGITUD_IDENTIFICADOR + LONGITUD_DESCRIPCION + LONGITUD_DIRECCION + LONGITUD_SALARIO; 
    
    private File ruta;
    
    public FicheroReformas(String ruta){
        this.ruta = new File(ruta);
    }
    
    public int getCARACTERES_DESCRIPCION() {
        return CARACTERES_DESCRIPCION;
    }
    
    public int getLONGITUD_DESCRIPCION() {
        return LONGITUD_DESCRIPCION;
    }
    
    public int getCARACTERES_DIRECCION() {
        return CARACTERES_DIRECCION;
    }
    
    public int getLONGITUD_DIRECCION() {
        return LONGITUD_DIRECCION;
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