/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.filesstreams.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 2 oct 2024
 *
 */
public class Lectura extends Fichero{

    public Lectura(String ruta) {
        super(ruta);
    }
   
    /**
     * Lee el contenido de un archivo carácter a carácter
     * 
     * @return Cadena con el contenido del archivo
     */
    public String leerCaracterACaracter(){
        
        FileReader ficheroIn = null;
        StringBuffer texto = new StringBuffer();
        
        try {          
            //Creamos el flujo de lectura:
            ficheroIn = new FileReader(getRuta());
            //Leemos el código ASCII del carácter contenido en el fichero
            int i;
            
            i= ficheroIn.read();
            
            while (i != -1) {              
                texto.append((char)i);
                i = ficheroIn.read();
            } 
     
        } catch (IOException ex) {
            Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
        
        } finally {
            try {
                ficheroIn.close();
                
            } catch (IOException ex) {
                Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return texto.toString();
        
    }
    
    /**
     * Lee el contenido de un archivo utilizando un array de caracteres
     * 
     * @return Cadena con el contenido del archivo
     */
    public String leerArrayCaracteres(){
        
        FileReader ficheroIn = null;
        StringBuffer texto = new StringBuffer();
        
        try {
           
            //Creamos el flujo de lectura:
            ficheroIn = new FileReader(getRuta());

            int i;
            char cadena[] = new char[5];
            i = ficheroIn.read(cadena);
            
            while (i!=-1) {   
                texto.append(cadena);
                Arrays.fill(cadena, ' ');
                i= ficheroIn.read(cadena);
            }           
        } catch (IOException ex) {
            Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ficheroIn.close();               
            } catch (IOException ex) {
                Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }      
        return texto.toString();
    }
    
    /**
     * Lee el contenido de un archivo utilizando un BufferedReader
     * 
     * 
     * @return Cadena con el contenido del archivo
     */
    public String leerCaracteresBufferReader(){
        FileReader ficheroIn = null;
        StringBuffer texto = new StringBuffer();

        try {

            //int i = 0;
            ficheroIn = new FileReader(getRuta());
            
            BufferedReader bufferficheroIn = new BufferedReader(ficheroIn);
            
            String linea;
            
            linea = bufferficheroIn.readLine();
            
            while (linea != null){
                texto.append(linea).append("\n");
                linea = bufferficheroIn.readLine();
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ficheroIn.close();               
            } catch (IOException ex) {
                Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
        //System.out.println(texto.toString()); Traza para revisar que funciona bien
        return texto.toString();
    }
    
    
    
}
