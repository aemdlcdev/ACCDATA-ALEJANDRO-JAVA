/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.ejercicio1examen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 13 nov 2024
 *
 */
public class Modelo {
    
    public void busquedaEjercicio1(String nombreArchivo, String rutaPartida, String rutaCopia){
    
        try {
            String ruta = utilidadBusquedaArchivo(rutaPartida,nombreArchivo);
            
            Path archivo = Paths.get(ruta,nombreArchivo);
            Path archivoDestino = Paths.get(rutaCopia,nombreArchivo);
            
            if (!Files.exists(archivoDestino.getParent())) {
                Files.createDirectories(archivoDestino.getParent());
            }
            
            Files.copy(archivo, archivoDestino);
            
        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public String utilidadBusquedaArchivo(String rutaPartida, String nombreArchivo) {
        File directorio = new File(rutaPartida);
        File[] archivos = directorio.listFiles();

        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    String resultado = utilidadBusquedaArchivo(archivo.getAbsolutePath(), nombreArchivo);
                    if (!resultado.isEmpty()) {
                        return resultado;
                    }
                } else if (archivo.getName().equals(nombreArchivo)) {
                    System.out.println("Archivo: " + archivo.getParent());
                    return archivo.getParent();
                }
            }
        }
        return "";
    }
    
}
