/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.filesstreams.controlador;

import com.aem.filesstreams.modelo.Lectura;
import com.aem.filesstreams.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 2 oct 2024
 *
 */
public class ControlLectura implements ActionListener {
    
    private final InterfazVista vista;
    private Lectura modelo;
    
    public ControlLectura(InterfazVista vista, Lectura modelo){
        this.vista=vista;
        this.modelo=modelo;
        this.vista.setControlador(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        String path = vista.getPath();
        this.modelo = new Lectura(path);
        
        switch(evento.getActionCommand()){
            case InterfazVista.LEE_CHAR_UNO_A_UNO_BUFFERED -> {
                modelo.leerCaracterACaracter();
            }
            case InterfazVista.LEE_CHAR_ARRAY_5 -> {
                modelo.leerArrayCaracteres();
            }
            case InterfazVista.LEE_LINEAS_BUFFERED_READER ->{
                modelo.leerCaracteresBufferReader();
            }
        }
    }

}
