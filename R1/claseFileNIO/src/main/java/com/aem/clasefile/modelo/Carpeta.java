/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.clasefile.modelo;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * 
     */
    public void crearCarpeta(String nombreDirectorio){
        Path p = Paths.get(nombreDirectorio);
        
        if (Files.exists(p)){
            //return "El directorio ya existe";
        } else{
            try {
                Path donePath = Files.createDirectories(p);
                //return "El directorio ha sido creado";
            } catch (IOException ex) {
                Logger.getLogger(Carpeta.class.getName()).log(Level.SEVERE, null, ex);
                //return ex.getMessage();
            }
        }
    }
    
    public void crearCarpeta(File directorioRaiz, String nombreDirectorio){
        File directorioNuevo = new File(directorioRaiz,nombreDirectorio);
        directorioNuevo.mkdir();
    }
    
    public void crearCarpeta(Path directorioRaiz, String nombreDirectorio){
        crearCarpeta(directorioRaiz.toString()+"\\"+nombreDirectorio);
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
    
    public String[] muestraContenidoCarpeta(){
        Path directorio = Paths.get(this.ruta);
        StringBuilder contenido = new StringBuilder();
        if(Files.exists(directorio) && Files.isDirectory(directorio)){
            DirectoryStream<Path> lista;
          try{
             lista = Files.newDirectoryStream(directorio); //Me guardo en una lista todos los objetos files del directorio
             for(Path p : lista){ //Recorro la lista
                BasicFileAttributes atributos = Files.readAttributes(p, BasicFileAttributes.class);
                if(atributos.isDirectory()){
                   System.out.println("[DIRECTORIO] " + p.getFileName());
                   contenido.append("[DIRECTORIO] " + p.getFileName());
                } else if(atributos.isRegularFile()){
                   System.out.println("[ARCHIVO] " + p.getFileName() + " - Tamanio: " + atributos.size() + " bytes");
                   contenido.append("[ARCHIVO] " + p.getFileName() + " - Tamanio: " + atributos.size() + " bytes");
                  }    
             }
          } catch(IOException ex){            
              contenido.append("Error al leer el directorio: " + ex.getMessage());
          }           
        } else {
                contenido.append("El directorio esta vacio");
          }
        return contenido.toString().split("\n");
    }
    
    /**
     * Elimina todos los archivos y directorios dentro del directorio especificado.
     *
     * @param rutaDirectorio La ruta del directorio cuyo contenido se va a eliminar.
     */
    public void eliminarContenidoDirectorio(String rutaDirectorio) throws IOException {
        Path directorio = Paths.get(rutaDirectorio);
        
        if (!Files.exists(directorio) || !Files.isDirectory(directorio)) {
            System.out.println("El directorio especificado no existe o no es un directorio.");
        }else{
            //File[] archivos = directorio.listFiles();
            DirectoryStream<Path> lista = Files.newDirectoryStream(directorio);
        
            for (Path archivo : lista) {
                try {
                    if (Files.isDirectory(archivo)) { //Caso base: el caso base es que deje de haber directorios
                        eliminarContenidoDirectorio(archivo.toAbsolutePath().toString()); //Lo llamo recursivamente por si hay subdirectorios y los vaya borrando
                    }
                    if (!Files.deleteIfExists(archivo) && Files.isRegularFile(archivo)) { //Si devuelve false es que no se ha podido eliminar
                        System.out.println("No se pudo eliminar: " + archivo.toAbsolutePath().toString());
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Carpeta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Files.delete(directorio); //Borro el directorio al final
        }
    }
}
