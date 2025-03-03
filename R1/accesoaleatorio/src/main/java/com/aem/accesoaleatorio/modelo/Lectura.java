/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.accesoaleatorio.modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 9 oct 2024
 *
 */
public class Lectura extends FicheroEmpleados{

    public Lectura(String ruta) {
        super(ruta);
    }
    
    public Empleado lecturaEmpleado(int identificador){
        RandomAccessFile randomFile = null;
        Empleado empleado = new Empleado();
        try {
            
            int posicion = 0;
            
            byte[] cadena = new byte[super.getLONGITUD_APELLIDO()];
            
            randomFile = new RandomAccessFile(getRuta(),"r");
            
            posicion = (identificador-1)* super.getLONGITUD_TOTAL();
            
            try {
                if (posicion < randomFile.length()){
                    randomFile.seek(posicion);
                    
                    // Leemos el identificador
                    empleado.setIdentificador(randomFile.readLong());
                    
                    // Leemos el apellido
                    randomFile.read(cadena);
                    empleado.setApellido(new String(cadena));
                    
                    // Leemos el numero del departamento
                    empleado.setDepartamento(randomFile.readInt());
                    
                    // Leemos el salario
                    empleado.setSalario(randomFile.readDouble());
                }
            } catch (IOException ex) {
                Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                randomFile.close();
            } catch (IOException ex) {
                Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        System.out.println(empleado.toString());
        return empleado;
    } 
    
    public void muestraRegistros() {
        RandomAccessFile randomFile = null;
        try {
            randomFile = new RandomAccessFile(getRuta(), "r");
            long posicion = 0; // Empezamos desde el principio

            // Verificamos si el archivo tiene contenido
            if (randomFile.length() != 0) {
                // Ciclo para leer registros
                while (posicion < randomFile.length()) {
                    randomFile.seek(posicion); // Nos posicionamos en el registro actual

                    // Crear un nuevo objeto Empleado
                    Empleado empleado = new Empleado();

                    // Leemos el identificador
                    empleado.setIdentificador(randomFile.readLong());

                    // Leemos el apellido
                    byte[] cadena = new byte[super.getLONGITUD_APELLIDO()];
                    randomFile.read(cadena);
                    empleado.setApellido(new String(cadena).trim()); // Trim para eliminar espacios extra

                    // Leemos el número del departamento
                    empleado.setDepartamento(randomFile.readInt());

                    // Leemos el salario
                    empleado.setSalario(randomFile.readDouble());

                    if (empleado.getIdentificador() > 0) {
                        System.out.println(empleado.toString());
                    }
                    // Me muevo a la posicion del siguiente registro
                    posicion += super.getLONGITUD_TOTAL();
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (randomFile != null) {
                    randomFile.close();
                }
            } catch (IOException e) {
                Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
    public List<Empleado> leerTodosLosEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        int identificador = 1;  // Comienza desde el primer empleado

        while (true) {
            Empleado empleado = lecturaEmpleado(identificador);

            // Verificar si el empleado leído es válido
            if (empleado.getIdentificador() == 0) {
                // Suponiendo que identificador = 0 indica que no se leyó un empleado válido
                break; 
            }

            // Agregar el empleado a la lista
            empleados.add(empleado);

            // Incrementar el identificador para leer el siguiente empleado
            identificador++;
        }

        return empleados;
    }

    
}

    

