/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.filesstreams.controlador;

import com.aem.filesstreams.modelo.Escritura;
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
public class ControlEscritura implements ActionListener {
    
    private final InterfazVista vista;
    private Escritura modelo;
    
    public ControlEscritura(InterfazVista vista, Escritura modelo){
        this.vista=vista;
        this.modelo=modelo;
        this.vista.setControlador(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        String path = vista.getPath();
        this.modelo = new Escritura(path);
        boolean existe = modelo.existeFile(path);
        switch (evento.getActionCommand()){
            case InterfazVista.ESCRIBE_CHAR_A_CHAR -> {
                char c = vista.getChar();
                modelo.escribirCaracter(c, existe);
            }
            case InterfazVista.ESCRIBE_CHAR_ARRAY -> {
                modelo.escribirArrayCaracteres(modelo.getArray(vista.getEscribir()), existe);
            }
            case InterfazVista.ESCRIBRE_BUFFERED -> {
                modelo.escribirStreamBufferedCaracteres(vista.getEscribir(), existe);
            }
            case InterfazVista.ESCRIBE_PRINTWRITER -> {
                modelo.escribirBufferedPrintCaracteres(vista.getEscribir(), existe);
            }
            case InterfazVista.ENCRIPTAR -> {
                modelo.encriptarArchivo();
            }
            case InterfazVista.DESENCRIPTAR -> {
                modelo.desencriptarArchivo();
            }
        }
        
    }
    

}
