/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.clasefile.modelo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


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
    public void creaArchivo(String ruta, String nombre) throws IOException{
        File archivo = new File(ruta,nombre);
        archivo.createNewFile();
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
        File archivo = new File(ruta);
        if(archivo.exists()){
            if(archivo.isDirectory()){                   
            File[] listaF = archivo.listFiles();               
            for (File file:listaF){
                if(file.isFile()){
                    file.delete();
                    System.out.println("Archivo " + file.getName() + " eliminado");
                }
            }                           
        }
            archivo.delete();
            System.out.println("Borrado correctamente");
        }
        else{
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
        File archivo = new File(ruta, nombre);
        File archivoNuevo = new File(archivo.getParent(), nombreNuevo);

            if (!archivo.exists()) {
                System.out.println("El archivo especificado no existe o no es un archivo regular");
            }else{           
                try {
                    boolean exito = archivo.renameTo(archivoNuevo);
                        if (exito) {
                            System.out.println("El archivo se ha renombrado con exito");
                        } else {
                            System.out.println("No se ha podido renombrar el archivo");
                        }
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
        File archivo = new File(ruta, nombreArchivo);
        File archivoDestino = new File(rutaDestino, nombreArchivo);

            if (!archivo.exists()) {
                throw new IOException("El archivo no existe");
            }

            if (!archivo.isFile()) {
                throw new IOException("El archivo especificado no es un archivo regular");
            }

            if (!archivoDestino.getParentFile().exists()) {
                archivoDestino.getParentFile().mkdirs();
            }

            if (!archivoDestino.exists()) {
                archivoDestino.createNewFile();
            }
            
        Files.copy(archivo.toPath(), archivoDestino.toPath());
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
        File origen = new File(rutaOrigen);
        File destino = new File(rutaDestino);
        destino.getParentFile().mkdirs();   
        origen.renameTo(destino);
    }
        
}
