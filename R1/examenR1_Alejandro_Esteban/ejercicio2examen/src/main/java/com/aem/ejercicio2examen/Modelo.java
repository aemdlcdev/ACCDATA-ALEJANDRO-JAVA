/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.ejercicio2examen;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.aem.ejercicio2examen.Reforma;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 13 nov 2024
 *
 */
public class Modelo extends FicheroReformas{

    public Modelo(String ruta) {
        super(ruta);
    }
    
    public void insertarReforma(Reforma reforma){
        RandomAccessFile randomFile = null;
        long posicion = 0;
        StringBuffer bufferDescripcion = null;
        StringBuffer bufferDireccion = null;
        
        try {
            
            randomFile = new RandomAccessFile(getRuta(),"rw");
            
            posicion=(reforma.getId()-1)* super.getLONGITUD_TOTAL();
            
            if (randomFile.length() != 0) {
                posicion = (reforma.getId() - 1) * super.getLONGITUD_TOTAL();
                randomFile.seek(posicion); // Mover el puntero a la posición correcta
                if (reforma.getId() == randomFile.readLong()) { // Verificar el identificador
                    System.out.println("La reforma ya existe, modificaremos el coste");
                    randomFile.seek(posicion);
                    randomFile.writeDouble(reforma.getCoste());
                } else {
                    randomFile.seek(posicion);
                    randomFile.writeLong(reforma.getId());

                    // DESCRIPCION
                    bufferDescripcion = new StringBuffer(reforma.getDescripcion());
                    bufferDescripcion.setLength(super.getCARACTERES_DESCRIPCION());
                    randomFile.writeChars(bufferDescripcion.toString());

                    // DIRECCION
                    bufferDireccion = new StringBuffer(reforma.getDireccion());
                    bufferDireccion.setLength(super.getCARACTERES_DIRECCION());
                    randomFile.writeChars(bufferDireccion.toString());
                    // COSTE
                    randomFile.writeDouble(reforma.getCoste());
                }
            }
                         
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                randomFile.close();
            } catch (IOException ex) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
    
    public void muestraEjercicio2(int id){
    
        RandomAccessFile randomFile = null;
        Reforma reforma = new Reforma();
        try {
            
            int posicion = 0;
            
            byte[] cadenaDescripcion = new byte[super.getLONGITUD_DESCRIPCION()];
            byte[] cadenaDireccion = new byte[super.getLONGITUD_DIRECCION()];
            
            randomFile = new RandomAccessFile(getRuta(),"r");
            
            posicion = (id-1)* super.getLONGITUD_TOTAL();
            
            try {
                if (posicion < randomFile.length()){
                    randomFile.seek(posicion);
                    
                    // Leemos el identificador
                    reforma.setId(randomFile.readLong());
                    
                    // Leemos la descripcion
                    randomFile.read(cadenaDescripcion);
                    reforma.setDescripcion(new String(cadenaDescripcion));
                    
                    // Leemos la direccion
                    randomFile.read(cadenaDireccion);
                    reforma.setDireccion(new String(cadenaDireccion));
                    
                    // Leemos el coste
                    reforma.setCoste(randomFile.readDouble());
                }
            } catch (IOException ex) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                randomFile.close();
            } catch (IOException ex) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        System.out.println("Descripcion: " + reforma.getDescripcion() + " Coste: " + reforma.getCoste()); 
    }
    
}
