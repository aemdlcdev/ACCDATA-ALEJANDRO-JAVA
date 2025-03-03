/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.clasefile.vista;

import com.aem.clasefile.controlador.ControlArchivo;
import com.aem.clasefile.controlador.ControlCarpeta;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JTextArea;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 18 sept 2024
 *
 */
public class CarpetaVistaTexto implements InterfazVista {
    
    private ControlCarpeta controlador;
    private ControlArchivo controladorA;
    private final BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
    
    private int leeOpcion() {
        String s = null;
        try {
            s = in.readLine();
            return Integer.parseInt(s);
        } catch (IOException | NumberFormatException e) {
            operacionIncorrecta();
            return 0;
        }
    }
    
    private String leeString(){
        try{
            String s = in.readLine();
            return s;
        } catch(IOException e){
            System.out.println("Error en la cadena introducida!");
            return leeString();
        }
    }
    
    private void solicitaOperacion() {
        System.out.println("Indica la operacion que quiere realizar:");
        System.out.println("1: Crear carpeta");
        System.out.println("2: Crear carpeta pasando la ruta padre y el nombre de la carpeta");
        System.out.println("3: Crear carpeta pasando un file y el nombre de la carpeta");
        System.out.println("4: Mostrar contenido de un directorio");
        System.out.println("5: Borrar archivo u archivos de un directorio");
        System.out.println("6: Renombrar archivo indicando la ruta y el nombre del archivo nuevo");
        System.out.println("7: Copia archivo indicando ruta nueva");
        System.out.println("8: Eliminar directorios y archivos");
        System.out.println("0: Salir");
    }
    
    private void procesaNuevaOperacion() {
        int operacion;
        solicitaOperacion();
        operacion = leeOpcion();
        switch (operacion) {
            case 0 -> {
                System.out.println("Adios.");
                System.exit(0);
            }
            case 1 -> controlador.actionPerformed(new ActionEvent(this, operacion, CREARCARPETARUTACOMPLETA));
            case 2 -> controlador.actionPerformed(new ActionEvent(this, operacion, CREARCARPETARUTACOMPLETAPADREYNOMBRE));
            case 3 -> controlador.actionPerformed(new ActionEvent(this, operacion, CREARCARPETACOMPLETAFILEPADREYNOMBRE));
            case 4 -> controlador.actionPerformed(new ActionEvent(this, operacion, RECORREYMUESTRA));
            case 5 -> controladorA.actionPerformed(new ActionEvent(this, operacion, ELIMINAARCHIVO));
            case 6 -> controladorA.actionPerformed(new ActionEvent(this, operacion,RENOMBRAARCHIVO));
            case 7 -> controladorA.actionPerformed(new ActionEvent(this, operacion,COPIAARCHIVO));
            case 8 -> controlador.actionPerformed(new ActionEvent(this, operacion,ELIMINATODO));
            
            default -> operacionIncorrecta();
        }
        procesaNuevaOperacion();
    }
    
    private void operacionIncorrecta() {
        System.out.print("Operacion incorrecta. ");
        procesaNuevaOperacion();
    }
    
    @Override
    public void setControlador(ControlCarpeta c) {
        this.controlador=c;
    }
    
    @Override
    public void setControlador(ControlArchivo ca){
        this.controladorA=ca;
    }

    @Override
    public void arranca() {
        procesaNuevaOperacion();
    }

    @Override
    public String getRuta() {
        System.out.print("Ruta a seleccionar: "); 
        return leeString();
    }
    
    @Override
    public String getNombre(){
        System.out.println("Introduce el nombre:");
        return leeString();
    }
    
    @Override 
    public String getNombreNuevo(){
        System.out.println("Introduce el nombre nuevo");
        return leeString();
    }
    
    @Override
    public String getRutaNueva(){
        System.out.println("Introduce la nueva ruta:");
        return leeString();
    }

    @Override
    public JTextArea getTextArea() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    @Override
    public void recorreYMuestra(String[] s) {
        
    }

    
}
