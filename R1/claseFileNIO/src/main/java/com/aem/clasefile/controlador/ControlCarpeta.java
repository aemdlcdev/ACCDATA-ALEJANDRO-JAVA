/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.clasefile.controlador;

import com.aem.clasefile.modelo.Carpeta;
import com.aem.clasefile.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Clase encargada de comunicar la vista con el modelo
 * Como esta clase esta interesada en procesar un evento de accion entonces
 * debe implementar la interfaz ActionListener
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 18 sept 2024
 *
 */
public class ControlCarpeta implements ActionListener{

    private  final InterfazVista vista;
    private final Carpeta modelo;

    public ControlCarpeta(InterfazVista vista, Carpeta modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.setControlador(this);
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        
        String ruta = vista.getRuta();
        modelo.setRuta(ruta);
        
        
        switch (evento.getActionCommand()){
            case InterfazVista.CREARCARPETARUTACOMPLETA -> {
                modelo.crearCarpeta();
            }
            case InterfazVista.CREARCARPETARUTACOMPLETAPADREYNOMBRE -> {
                String nombre = vista.getNombre();
                modelo.crearCarpeta(nombre);
            }
            case InterfazVista.CREARCARPETACOMPLETAFILEPADREYNOMBRE -> {
                String nombre = vista.getNombre();
                modelo.crearCarpeta(modelo.getFileDeRuta(), nombre);
            }
            
            case InterfazVista.RECORREYMUESTRA -> {
                
                vista.recorreYMuestra(modelo.muestraContenidoCarpeta());
            }
            
            case InterfazVista.ELIMINATODO -> {
            try {
                modelo.eliminarContenidoDirectorio(ruta);
            } catch (IOException ex) {
                Logger.getLogger(ControlCarpeta.class.getName()).log(Level.SEVERE, null, ex);
            }
            }   
        }
    }
    
}
