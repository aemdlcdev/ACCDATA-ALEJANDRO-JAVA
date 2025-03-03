/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.ejercicio3examen.controlador;

import com.aem.ejercicio3examen.modelo.GestionContenidoDom;
import com.aem.ejercicio3examen.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 13 nov 2024
 *
 */
public class Controlador implements ActionListener{
    
    private  final InterfazVista vista;
    private final GestionContenidoDom modelo;
    

    public Controlador(InterfazVista vista, GestionContenidoDom modelo) {
        
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.setControlador(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        
        String nombre = vista.getNombre();
        double cantidad = vista.getCantidad();
        String superm = vista.getSuper();
        
        switch (evento.getActionCommand()){
            
            case InterfazVista.INSERTAR_PRODUCTO -> {  
                modelo.añadirProducto(nombre, cantidad, superm);
            }
        }
    }    
    
}