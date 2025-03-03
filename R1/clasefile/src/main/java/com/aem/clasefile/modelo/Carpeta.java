/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.clasefile.modelo;

import java.awt.List;
import java.io.File;
import java.util.ArrayList;

/**
 * Clase encargada de la logica de la applicacion
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 18 sept 2024
 *
 */
public class Carpeta {
    
    private String ruta;
    
    /**
     * Constructor de la clase
     * @param ruta es la ruta de la carpeta
     */
    public Carpeta(String ruta) {
        this.ruta = ruta;
    }
    
    /**
     * Constructor vacio de la clase
     */
    public Carpeta() {
    }
    
    public String getRuta() {
        return ruta;
    }
    
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    /**
     * Crea un directorio en la ruta indicada
     * Utiliza el atributo de la clase
     */
    public void crearCarpeta(){
        File directorioNuevo = new File(this.ruta);
        directorioNuevo.mkdir();
    }
    
    /**
     * Crea un directorio en la ruta indicada
     * Utiliza el constructor al cual le pasamos la ruta del directorio padre
     * y el nombre del nuevo directorio
     * @param nombreDirectorio Nombre del nuevo directorio
     */
    public void crearCarpeta(String nombreDirectorio){
        File directorioNuevo = new File(ruta,nombreDirectorio);
        directorioNuevo.mkdir();
    }
    
    public void crearCarpeta(File directorioRaiz, String nombreDirectorio){
        File directorioNuevo = new File(directorioRaiz,nombreDirectorio);
        directorioNuevo.mkdir();
    }
    
    /**
     * Devuelve en un file la ruta de la carpeta
     * @return File que apunta a la carpeta
     */
    
    public File getFileDeRuta(){
        File directorioRaiz = new File(this.ruta);
        return directorioRaiz;
    }
    
    
    /**
    * Muestra el contenido de un directorio especificado por la ruta.
    * Si la ruta corresponde a un directorio válido, se listan todos los archivos y subdirectorios
    * contenidos en él. Para cada archivo, se muestra su nombre y tamaño en bytes. Para cada subdirectorio,
    * se muestra su nombre.
    * @param nombreRuta La ruta del directorio cuyo contenido se desea mostrar.
    */
    
    public String[] muestraContenidoCarpeta() {
        File directorio = new File(this.ruta);
        ArrayList<String> contenido = new ArrayList<String>(); // Usamos una lista temporal para guardar cada línea

        if (directorio.exists() && directorio.isDirectory()) {
            File[] listaFiles = directorio.listFiles();
            if (listaFiles != null && listaFiles.length != 0) {
                for (File file : listaFiles) {
                    if (file.isDirectory()) {
                        contenido.add("Directorio: " + file.getName()); // Añadimos el contenido a la lista
                    } else if (file.isFile()) {
                        contenido.add("Archivo: " + file.getName() + " Tamanio: " + file.length() + " bytes");
                    }
                }
            } else {
                contenido.add("El directorio está vacío");
            }
        } else {
            contenido.add("Ruta inválida o lo indicado no es un directorio");
        }

        return contenido.toArray(new String[0]); // Convertimos la lista a array de String
    }

    
    /**
     * Elimina todos los archivos y directorios dentro del directorio especificado.
     *
     * @param rutaDirectorio La ruta del directorio cuyo contenido se va a eliminar.
     */
    public void eliminarContenidoDirectorio(String rutaDirectorio) {
        File directorio = new File(rutaDirectorio);
        
        if (!directorio.exists() || !directorio.isDirectory()) {
            System.out.println("El directorio especificado no existe o no es un directorio.");
        }else{

            File[] archivos = directorio.listFiles();
        
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    eliminarContenidoDirectorio(archivo.getAbsolutePath()); //Lo llamo recursivamente por si hay subdirectorios
                }
                if (!archivo.delete()) { //Si devuelve false es que no se ha podido eliminar
                    System.out.println("No se pudo eliminar: " + archivo.getAbsolutePath());
                }
            }
            directorio.delete();
        }
    }
}
