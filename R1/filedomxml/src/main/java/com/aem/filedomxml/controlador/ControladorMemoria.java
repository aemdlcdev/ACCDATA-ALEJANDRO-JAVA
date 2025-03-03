/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.filedomxml.controlador;


import com.aem.filedomxml.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.aem.filedomxml.modelo.GestionContenidoDom;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 17 oct 2024
 *
 */
public class ControladorMemoria implements ActionListener{
    
    private InterfazVista vista;
    private GestionContenidoDom modelo;

    public ControladorMemoria(InterfazVista vista, GestionContenidoDom modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }
    
    

    @Override
    public void actionPerformed(ActionEvent evento) {
        
        
    }
    
    
    
}
