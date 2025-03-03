/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.filedomxml.vista;

import com.aem.filedomxml.controlador.Controlador;
import com.aem.filedomxml.controlador.ControladorMemoria;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 17 oct 2024
 *
 */
public class VistaTexto implements InterfazVista{

    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private Controlador controlador;
    private ControladorMemoria controladorMemoria;
    
    
    // Metodos leer
    
    private int leeOpcion(){
        String s = null;
        try{
            s = in.readLine();
            return Integer.parseInt(s);
        } catch (IOException | NumberFormatException ex){
            operacionIncorrecta();
            return 0;
        }
    }
    
    private String leeString(){
        String s = null;
        try{
            s = in.readLine();
            return s;
        } catch (IOException | NumberFormatException ex){
            System.out.println("Error en la cadena introducida " + ex.getMessage());
            return leeString();
        }
    }
    
    // Menus
    
    private void procesaNuevaOperacion(){
    
        int opcion;
        solicitaOperacion();
        opcion=leeOpcion();
        
        switch (opcion){
            
            case 1 -> {
                controlador.actionPerformed(new ActionEvent(this, opcion, AÑADIRNODO));
            }
            
        }
    }
    
    private void solicitaOperacion(){
        System.out.println("1. Añadir nodo");
        System.out.println("2. Añadir nodo hijo");
        System.out.println("3. Añadir nodo hijo con texto");
        System.out.println("4. Mostrar documento XML en pantalla");
        System.out.println("5. Generar archivo XML");
        System.out.println("6. Cargar archivo en memoria");
        System.out.println("7. Obtener elemento principal del documento XML");
        System.out.println("8. Obtener valor de un elemento");
        System.out.println("9. Obtener empleado");
        System.out.println("10. Obtener lista de empleados");
        System.out.println("0. Salir");
    }
    
    
    
    private void operacionIncorrecta(){
        System.out.println("Operacion no válida!");
        procesaNuevaOperacion();
    }
    
    @Override
    public void setControlador(Controlador c) {
        this.controlador=c;
    }
    
    @Override
    public void setControladorMemoria(ControladorMemoria cM){
        this.controladorMemoria=cM;
    }

    @Override
    public void arranca() {
        procesaNuevaOperacion();
    }

    @Override
    public String getNombreNodo() {
        System.out.println("Introduce el nombre del nodo raiz:");
        return leeString();
    }
    
    @Override
    public String getNombreDocumento(){
        System.out.println("Introduce el nombre del documento XML");
        return leeString();
    }
}
