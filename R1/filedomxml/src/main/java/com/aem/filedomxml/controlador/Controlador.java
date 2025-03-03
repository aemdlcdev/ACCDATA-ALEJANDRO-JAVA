/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.filedomxml.controlador;

import com.aem.filedomxml.modelo.GestionContenidoDom;
import com.aem.filedomxml.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 17 oct 2024
 *
 */
public class Controlador implements ActionListener{
    
    private InterfazVista vista;
    private GestionContenidoDom modelo;

    public Controlador(InterfazVista vista, GestionContenidoDom modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
    }

    
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        modelo = new GestionContenidoDom(vista.getNombreDocumento());
        
        switch(evento.getActionCommand()){
            case InterfazVista.AÑADIRNODO -> {
                modelo.addNodo(vista.getNombreNodo());
            }
            
        }
        
    }
   
    
    
}
