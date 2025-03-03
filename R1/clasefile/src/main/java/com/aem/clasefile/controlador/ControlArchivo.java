/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.clasefile.controlador;

import com.aem.clasefile.modelo.Archivo;
import com.aem.clasefile.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 20 sept 2024
 *
 */
public class ControlArchivo implements ActionListener{
    
    private  final InterfazVista vista;
    private final Archivo modelo;
    

    public ControlArchivo(InterfazVista vista, Archivo modelo) {
        
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.setControlador(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        
        String ruta = vista.getRuta();
        modelo.setRuta(ruta);
        
        switch (evento.getActionCommand()){
            
            case InterfazVista.ELIMINAARCHIVO -> {  
                modelo.borraArchivo(ruta);
            }
            
            case InterfazVista.RENOMBRAARCHIVO -> {
                String nombre = vista.getNombre();
                String nombreNuevo = vista.getNombreNuevo();
                modelo.renombraArchivo(ruta, nombre, nombreNuevo);
            }
            
            case InterfazVista.COPIAARCHIVO -> {
                String rutaNueva = vista.getRutaNueva();
                String nombreArchivo = vista.getNombre();
                try { 
                    modelo.copiaArchivo(ruta, nombreArchivo, rutaNueva);
                } catch (IOException ex){
                    ex.getMessage();
                }                
            }
        }
    }    
    
}
