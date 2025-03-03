/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.filedomxml.modelo;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 18 oct 2024
 *
 */

public class EmpleadoLector {
    
    private boolean b;
    
    public List<Empleado> leerEmpleados(String rutaArchivo) {
        b=false;
        List<Empleado> empleados = new ArrayList<>();
        try (ObjectInputStream objetoSalida = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            while (b) {
                try {
                    Empleado empleado = (Empleado) objetoSalida.readObject();
                    empleados.add(empleado);
                } catch (EOFException e) {
                    b=true;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            try {
                e.printStackTrace();
                throw e;
            } catch (IOException ex) {
                Logger.getLogger(EmpleadoLector.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EmpleadoLector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return empleados;
    }
}
