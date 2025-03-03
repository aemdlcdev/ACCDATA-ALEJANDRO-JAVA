/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.filesstreams.modelo;

import java.io.File;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 2 oct 2024
 *
 */
public class Fichero {
    private File ruta;

    public Fichero(String ruta) {
        this.ruta = new File(ruta);
    }

    /**
     * Devuelve el atributo ruta
     * 
     * @return String con la ruta del archivo
     */
    public String getRuta() {
        return ruta.getAbsolutePath();
    }

    /**
     * Pone el valor al atributo ruta
     * 
     * @param ruta Ruta completa al archivo
     */
    public void setRuta(String ruta) {
        this.ruta = new File(ruta);
    }
       
}
