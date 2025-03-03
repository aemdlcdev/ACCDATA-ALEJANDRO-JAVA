/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.aem.ficherouniversidades;

import java.io.File;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 */
public class FicheroUniversidades {

    // Tamaño de datos en bytes   
    private final int LONGITUD_DOUBLE = 8;
    private final int LONGITUD_INT = 4;
    private final int LONGITUD_CHAR =2;
    
    // Tamaño fijado para la cadena de caracteres
    private final int CARACTERES_CARRERA_Y_CIUDAD = 10;
    
    // Tamaño de cada uno de los campos que forman el registro del empleado
    private final int LONGITUD_IDENTIFICADOR = LONGITUD_INT;
    private final int LONGITUD_CARRERA = CARACTERES_CARRERA_Y_CIUDAD * LONGITUD_CHAR;     
    private final int LONGITUD_CIUDAD = CARACTERES_CARRERA_Y_CIUDAD * LONGITUD_CHAR;
    private final int LONGITUD_NOTACORTE = LONGITUD_DOUBLE;
    
    // Tamaño total del registro
    private final int LONGITUD_TOTAL = LONGITUD_IDENTIFICADOR + LONGITUD_CARRERA + LONGITUD_CIUDAD + LONGITUD_NOTACORTE; 
    
    private File ruta;
    
    public FicheroUniversidades(String ruta){
        this.ruta = new File(ruta);
    }
    
    public int getCARACTERES_CARRERA_Y_CIUDAD() {
        return CARACTERES_CARRERA_Y_CIUDAD;
    }
    
    public int getLONGITUD_CIUDAD() {
        return LONGITUD_CIUDAD;
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
