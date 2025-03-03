/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.accesoaleatorio.modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 9 oct 2024
 *
 */
public class Escritura extends FicheroEmpleados {

    public Escritura(String ruta) {
        super(ruta);
    }
    
    public void escribirEmpleadoFinalArchivo(Empleado empleado) {
    RandomAccessFile randomFile = null;
    long posicion = 0;
    StringBuffer bufferStr = null;

    try {
        randomFile = new RandomAccessFile(getRuta(), "rw");

        if (randomFile.length() != 0) { // Compruebo si está vacío o no
            posicion = randomFile.length();
        }

        randomFile.seek(posicion); // Me muevo a la posición

        // Escribimos el identidicador
        randomFile.writeLong((posicion / super.getLONGITUD_TOTAL()) + 1); // Calculo el identificador que le corresponde

        // Escribimos el apellido
        bufferStr = new StringBuffer(empleado.getApellido());
        bufferStr.setLength(super.getCARACTERES_APELLIDO());
        randomFile.writeChars(bufferStr.toString());

        // Escribimos el número del departamento
        randomFile.writeInt(empleado.getDepartamento());

        // Escribimos el salario
        randomFile.writeDouble(empleado.getSalario());

    } catch (FileNotFoundException ex) {
        Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if (randomFile != null) {
                randomFile.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    
    public void escribirEmpleadoPorIdentificador(long identificador, String apellido, int numDept, double salario){
        RandomAccessFile randomFile = null;
        long posicion = 0;
        StringBuffer bufferStr = null;
        
        try {
            
            randomFile = new RandomAccessFile(getRuta(),"rw");
            
            posicion=(identificador-1)* super.getLONGITUD_TOTAL();

            randomFile.seek(posicion);

            randomFile.writeLong(identificador);
            
            // Apellido
            bufferStr = new StringBuffer(apellido);
            bufferStr.setLength(super.getCARACTERES_APELLIDO());
            randomFile.writeChars(bufferStr.toString());

            // Departamento
            randomFile.writeInt(numDept);
            // Salario
            randomFile.writeDouble(salario);
     
            
                       
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                randomFile.close();
            } catch (IOException ex) {
                Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
    
    public void borraResgistro(long identificador) {
    RandomAccessFile randomFile = null;
    long posicion = 0;
    try {
        randomFile = new RandomAccessFile(getRuta(), "rw");
        if (randomFile.length() != 0) {
            posicion = (identificador - 1) * super.getLONGITUD_TOTAL();
            randomFile.seek(posicion); // Mover el puntero a la posición correcta
            if (identificador == randomFile.readLong()) { // Verificar el identificador
                randomFile.seek(posicion); // Volver a la posición inicial del registro, se hace porque cuando hacemos el readLong, salta los bytes correspondiente al identificador
                randomFile.writeLong(0); // Marcar el registro como borrado
            } else {
                System.out.println("Identificador no coincide.");
            }
        }
    } catch (IOException ex) {
        Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        if (randomFile != null) {
            try {
                randomFile.close();
            } catch (IOException ex) {
                Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
    
    public void modificarApellido(long identificador, String apellido){
        RandomAccessFile randomFile = null;
        StringBuffer bufferStr = null;
        long posicion=0;
        try {
            
            randomFile = new RandomAccessFile(getRuta(),"rw");
            if(randomFile.length() != 0){
                
                posicion = (identificador-1) * super.getLONGITUD_TOTAL();
                randomFile.seek(posicion);
                
                if(identificador == randomFile.readLong()){ // Con el readLong() leo el long y se posiciona en apellido
                    // Modificamos el apellido
                    bufferStr = new StringBuffer(apellido);
                    bufferStr.setLength(10);
                    randomFile.writeChars(bufferStr.toString());
                    
                }
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                randomFile.close();
            } catch (IOException ex) {
                Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
