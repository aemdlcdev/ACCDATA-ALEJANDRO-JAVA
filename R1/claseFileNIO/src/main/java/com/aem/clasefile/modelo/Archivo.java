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
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 20 sept 2024
 *
 */
public class Archivo {
    private String ruta;
    
    public Archivo(String ruta){
        this.ruta=ruta;
    }
    
    public Archivo(){
    }
    
    public String getRuta(){
        return ruta;
    }
    
    public void setRuta(String ruta){
        this.ruta=ruta;
    }
    
    /**
     * Crea un archivo pasandole la ruta y el nombre del archivo
     * @param ruta donde quieres crear el archivo
     * @param nombre es el nombre del archivo
     * @throws IOException si no puede crear el archivo
     */
    public void creaArchivo(String ruta, String nombre) {
        Path p = Paths.get(ruta,nombre);
        if(Files.exists(p)){
            //return "el archivo ya existe";
        }else{
            try {
                Path donePath=Files.createFile(p);
            } catch (IOException ex) {
                Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }
    
    /**
     * Borra un archivo o directorio especificado por la ruta.
     * Si la ruta corresponde a un archivo, se elimina directamente.
     * Si la ruta corresponde a un directorio, se eliminan todos los archivos
     * contenidos en el directorio antes de eliminar el directorio en sí.
     * @param ruta La ruta del archivo a eliminar.
     */
    //Borra archivo opcion 2
    public void borraArchivo (String ruta){
        Path archivo = Paths.get(ruta);
        if(Files.exists(archivo)){
            if(Files.isDirectory(archivo)){                   
                DirectoryStream<Path> lista;
                try {
                    lista = Files.newDirectoryStream(archivo);
                    for (Path p:lista){
                        if(Files.isRegularFile(p)){
                            Files.delete(p);
                            System.out.println("Archivo " + p.getFileName() + " eliminado");
                            //return "Archivo " + p.getFileName() + " eliminado";
                        }
                    } 
                } catch (IOException ex) {
                    Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
                }
                                      
        }
        try {
            Files.delete(archivo);
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("El directiorio contiene mas directorios");
        }
            System.out.println("Borrado correctamente");
            
        } else{
            System.out.println("El archivo o directorio no existe");
            
        }    
    }
    
    /* Borrar archivo original
    public void borraArchivo (String ruta){
        File archivo = new File(ruta);
        if(archivo.isFile() && archivo.exists()){
            archivo.delete();
        }else{         
            File[] listaF = archivo.listFiles();               
            for (File file:listaF){
                if(file.isFile()){
                    file.delete();
                    System.out.println("Archivo " + file.getName() + " eliminado");
                }
            }               
            archivo.delete();
            System.out.println("Directorio borrado");
        }
    }
    */
    /**
     * Renombra un archivo en la ruta especificada.
     *
     * @param ruta La ruta del archivo a renombrar.
     * @param nombre Nombre del archivo que se quiere reenombrar
     * @param nombreNuevo El nuevo nombre para el archivo.
     */
    public void renombraArchivo(String ruta, String nombre, String nombreNuevo) {
        Path archivo = Paths.get(ruta,nombre);
        Path archivoNuevo = Paths.get(archivo.getParent().toString(), nombreNuevo);

            if (!Files.exists(archivo)) {
                System.out.println("El archivo especificado no existe o no es un archivo regular");
            }else{           
                try {
                        Files.move(archivo, archivoNuevo);
                            System.out.println("El archivo se ha renombrado con exito");                       
                } catch (Exception e) {
                    System.out.println("Ocurrio un error al renombrar el archivo: " + e.getMessage());
                }
            }    
}
    /**
     * Copia un archivo de una ruta a otra.
     *
     * @param ruta La ruta del archivo a copiar.
     * @param nombreArchivo nombre del archivo que queremos copiar
     * @param rutaDestino La ruta de destino donde se copiará el archivo.
     * @throws IOException Si ocurre un error durante la copia del archivo.
     */
    public void copiaArchivo(String ruta, String nombreArchivo, String rutaDestino) throws IOException {
        Path archivo = Paths.get(ruta, nombreArchivo);
        Path archivoDestino = Paths.get(rutaDestino, nombreArchivo);

            if (!Files.exists(archivo)) {
                System.out.println("El archivo no existe!");
            }

            if (!Files.isRegularFile(archivo)) {
                throw new IOException("El archivo especificado no es un archivo regular");
            }

            // Verificar si el directorio padre no existe y crearlo
            if (!Files.exists(archivoDestino.getParent())) {
                Files.createDirectories(archivoDestino.getParent()); // Crear directorios faltantes
            }
            
        Files.copy(archivo, archivoDestino);
        System.out.println("Archivo copiado con exito");
        System.out.println("");
    }
    /**
     * Mueve un archivo de una ruta a otra.
     *
     * @param rutaOrigen La ruta del archivo a mover.
     * @param rutaDestino La ruta de destino donde se moverá el archivo.
     */
    public void moverArchivo(String rutaOrigen, String rutaDestino) {
        Path origen = Paths.get(rutaOrigen);
        Path destino = Paths.get(rutaDestino);
        try {
            Files.move(origen,destino);
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}
