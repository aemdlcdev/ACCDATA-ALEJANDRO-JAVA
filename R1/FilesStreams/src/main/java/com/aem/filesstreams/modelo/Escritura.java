/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.filesstreams.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 2 oct 2024
 *
 */
public class Escritura extends Fichero{
    
    public Escritura(String ruta) {
         super(ruta);
    }
    
    /**
     * Escribe un carécter en un archivo
     * 
     * @param caracter      Carácter a escribir
     * @param sobreescribe  Si vamos a sobreescribir el archivo o no
     */
    public void escribirCaracter(char caracter, boolean sobreescribe){
        FileWriter ficheroOut = null;
        try {
            ficheroOut = new FileWriter(getRuta(), sobreescribe);
            
            ficheroOut.write(caracter);
            
            ficheroOut.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ficheroOut.close();
            } catch (IOException ex) {
                Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Escribe un array de caracteres en un archivo
     * 
     * @param caracteres    Array de caracteres a escribir en el archivo
     * @param sobreescribe  Si vamos a sobreescribir el archivo o no 
     */
    public void escribirArrayCaracteres(char[] caracteres, boolean sobreescribe){
        FileWriter ficheroOut = null;
        try {
            ficheroOut = new FileWriter(getRuta(), sobreescribe);
            
            ficheroOut.write(caracteres);
            
            ficheroOut.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ficheroOut.close();
            } catch (IOException ex) {
                Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
    
    /**
     * Escribe una cadena de caracteres en un archivo utilizando BufferedWriter
     * 
     * @param cadena        Cadena de caracteres a escribir en el archivo
     * @param sobreescribe  Si vamos a sobreescribir el archivo o no
     */
    public void escribirStreamBufferedCaracteres(String cadena, boolean sobreescribe){
        FileWriter ficheroOut = null;
        try {
            ficheroOut = new FileWriter(getRuta(), sobreescribe);
            BufferedWriter bufferficheroOut = new BufferedWriter(ficheroOut);
            bufferficheroOut.write(cadena);
            bufferficheroOut.newLine(); // Salto de linea
            
            //Guardamos los cambios en el fichero
            bufferficheroOut.flush();
        } catch (IOException ex) {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ficheroOut.close();
            } catch (IOException ex) {
                Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 

    /**
     * Escribe una cadena de caracteres en un archivo utilizando PrintWriter
     * 
     * @param cadena        adena de caracteres a escribir en el archivo
     * @param sobreescribe  Si vamos a sobreescribir el archivo o no
     */
    public void escribirBufferedPrintCaracteres(String cadena, boolean sobreescribe){
        FileWriter ficheroOut = null;
        try {
            ficheroOut = new FileWriter(getRuta(), sobreescribe);
            PrintWriter bufferficheroOut = new PrintWriter(ficheroOut);
            bufferficheroOut.println(cadena);
            
            //Guardamos los cambios del fichero
            bufferficheroOut.flush();
        } catch (IOException ex) {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ficheroOut.close();
            } catch (IOException ex) {
                Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
    
    public boolean existeFile(String path){
        File file = new File(path);
        if(file.exists() && file.isFile()){
            return true;
        }
        else{
            return false;
        }
    }
    
    public char[] getArray(String str){
        return str.toCharArray();
    }
    
    public void encriptarArchivo() {
        StringBuffer texto = new StringBuffer();  
        int desplazamiento = 3;  
        String rutaOriginal = getRuta(); //Ruta del archivo original
        String rutaEncriptada = rutaOriginal.replace(".txt", "_encriptado.txt"); //Crear una ruta para el archivo encriptado

        try (FileReader ficheroIn = new FileReader(rutaOriginal);
             BufferedReader br = new BufferedReader(ficheroIn)) {

            String linea;
            texto.append("ENCRYPTED\n");
            while ((linea = br.readLine()) != null) {
                StringBuffer lineaEncriptada = new StringBuffer();

                //Encripto caracter por caracter con un desplazamiento
                for (int i = 0; i < linea.length(); i++) {
                    char c = linea.charAt(i);          
                    //Solo encriptar letras y dejar otros caracteres (como espacios, signos, etc) sin cambiar
                    if (Character.isLetter(c)) {
                        char base;
                        if (Character.isUpperCase(c)) {
                            base = 'A';//Si es mayuscula, la base será 'A'
                        } else {
                            base = 'a';//Si es minuscula, la base será 'a'
                        }
                        c = (char) ((c - base + desplazamiento) % 26 + base); //Cifrado César
                    }

                    lineaEncriptada.append(c);
                }

                texto.append(lineaEncriptada).append("\n");
            }

            //Guardar el archivo encriptado en una nueva ubicacion (usando false para sobrescribir el archivo)
            setRuta(rutaEncriptada); //Cambiar la ruta para que apunte al archivo encriptado
            escribirStreamBufferedCaracteres(texto.toString(), false);

            System.out.println("Archivo encriptado creado: " + rutaEncriptada);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void desencriptarArchivo() {
        StringBuffer texto = new StringBuffer();  
        int desplazamiento = 3;  
        String rutaOriginal = getRuta();

        try (FileReader ficheroIn = new FileReader(rutaOriginal);
             BufferedReader br = new BufferedReader(ficheroIn)) {

            String linea = br.readLine();//Leer la primera línea para comprobar la firma

            //Verificar si el archivo esta encriptado
            if (linea == null || !linea.equals("ENCRYPTED")) {
                System.out.println("El archivo no esta encriptado!");
                return;//Si no esta encriptado, salir del metodo
            }

            //Desencriptar el archivo
            while ((linea = br.readLine()) != null) {
                StringBuffer lineaDesencriptada = new StringBuffer();           
                //Desencripto caracter por caracter con un desplazamiento
                for (int i = 0; i < linea.length(); i++) {
                    char c = linea.charAt(i);         
                    //Solo desencriptar letras y dejar otros caracteres (como espacios, signos, etc) sin cambiar
                    if (Character.isLetter(c)) {
                        char base;
                        if (Character.isUpperCase(c)) {
                            base = 'A';//Si es mayuscula, la base será 'A'
                        } else {
                            base = 'a';//Si es minuscula, la base será 'a'
                        }
                        c = (char) ((c - base - desplazamiento + 26) % 26 + base); //Desplazamiento inverso al del encriptar
                    }

                    lineaDesencriptada.append(c);
                }

                texto.append(lineaDesencriptada).append("\n");
            }

            escribirStreamBufferedCaracteres(texto.toString(), false);

            System.out.println("Archivo desencriptado y guardado correctamente.");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
