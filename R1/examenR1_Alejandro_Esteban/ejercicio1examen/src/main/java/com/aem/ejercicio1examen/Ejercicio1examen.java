/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.aem.ejercicio1examen;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 */
public class Ejercicio1examen {

    public static void main(String[] args) {
        
        Modelo m = new Modelo();

        String nombreArchivo = "hola.txt";
        String ruta = "./";
        String rutaCopia = ".";
        String prueba = m.utilidadBusquedaArchivo(ruta, nombreArchivo);
        
        m.busquedaEjercicio1(nombreArchivo, ruta, rutaCopia);
    }
}
