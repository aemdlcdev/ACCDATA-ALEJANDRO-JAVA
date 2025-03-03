/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.filesstreams.vista;

import com.aem.filesstreams.controlador.ControlEscritura;
import com.aem.filesstreams.controlador.ControlLectura;
import static com.aem.filesstreams.vista.InterfazVista.LEE_CHAR_ARRAY_5;
import static com.aem.filesstreams.vista.InterfazVista.LEE_CHAR_UNO_A_UNO_BUFFERED;
import static com.aem.filesstreams.vista.InterfazVista.LEE_LINEAS_BUFFERED_READER;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 2 oct 2024
 *
 */
public class VistaTexto implements InterfazVista {
    private ControlLectura controladorL;
    private ControlEscritura controladorE;
    private final BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
    
    
    private String leeString(){
        try{
            String s = in.readLine();
            return s;
        } catch(IOException e){
            System.out.println("Error en la cadena introducida!");
            return leeString();
        }
    }
    
    private int leeOpcion(){
        String s = null;
        try{
            s = in.readLine();
            return Integer.parseInt(s);
        } catch(IOException | NumberFormatException ex){
            operacionIncorrecta();
            return 0;
        }
    }
    
    private void procesaNuevaOperacion(){
        int operacionP;
        solicitaOperacionPrincipal();
        operacionP = leeOpcion();
        switch (operacionP){
            case 1->{
                int operacionL;
                solicitaOperacionLectura();
                operacionL = leeOpcion();
                switch (operacionL){
                    case 1 -> {
                        controladorL.actionPerformed(new ActionEvent(this, operacionL, LEE_CHAR_UNO_A_UNO_BUFFERED));
                    }
                    case 2 -> {
                        controladorL.actionPerformed(new ActionEvent(this, operacionL, LEE_CHAR_ARRAY_5));
                    }
                    case 3 -> {
                        controladorL.actionPerformed(new ActionEvent(this, operacionL, LEE_LINEAS_BUFFERED_READER));
                    }
                    case 0 -> {
                        procesaNuevaOperacion();
                    }
                    default ->{
                        operacionIncorrecta();
                    }
                }
            }
            
            case 2->{
                int operacionE;
                solicitaOperacionEscritura();
                operacionE=leeOpcion();
                switch (operacionE){
                    case 1 -> {
                        controladorE.actionPerformed(new ActionEvent(this, operacionE, ESCRIBE_CHAR_A_CHAR));
                    }
                    case 2 -> {
                        controladorE.actionPerformed(new ActionEvent(this, operacionE, ESCRIBE_CHAR_ARRAY));
                    }
                    case 3 -> {
                        controladorE.actionPerformed(new ActionEvent(this, operacionE, ESCRIBRE_BUFFERED));
                    }
                    case 4 -> {
                        controladorE.actionPerformed(new ActionEvent(this, operacionE, ESCRIBE_PRINTWRITER));
                    }  
                    case 5 -> {
                        controladorE.actionPerformed(new ActionEvent(this, operacionE, ENCRIPTAR));
                    }
                    case 6 -> {
                        controladorE.actionPerformed(new ActionEvent(this, operacionE, DESENCRIPTAR));
                    }
                    case 0 -> {
                        procesaNuevaOperacion();
                    }
                    default -> {
                        operacionIncorrecta();
                    }
                }
            } 
            case 0 -> {
                System.out.println("Saliendo del programa...");
                System.exit(0);  // Finaliza el programa
            }
            default -> {
                operacionIncorrecta();
            }
        }
        procesaNuevaOperacion();
    }    
    
    private void operacionIncorrecta(){
        System.out.println("Operacion incorrecta!");
        procesaNuevaOperacion();
    }
    
    private void solicitaOperacionPrincipal(){
        System.out.println("¿Con que desea trabajar?");
        System.out.println("1: Lector");
        System.out.println("2: Escritor");
        System.out.println("0: Salir del programa");
    }
    
    private void solicitaOperacionLectura(){
        System.out.println("Selecciona una operacion.");
        System.out.println("1: Leer caracteres uno a uno usando StringBuffer(50)");
        System.out.println("2: Leer caracteres usando un array(5)");
        System.out.println("3: Leer lineas usando BufferedReader");    
        System.out.println("0:Volver al menu principal");
    }
    
    private void solicitaOperacionEscritura(){
        System.out.println("1: Escribir o sobreescribir caracter a caracter");
        System.out.println("2: Escribir o sobreescribir usando array de caracteres");
        System.out.println("3: Escribir o sobreescribir usando BufferedWriter");
        System.out.println("4: Escribir o sobreescribir usando PrintWriter");
        System.out.println("5: Ecriptar archivo");
        System.out.println("6: Desencriptar archivo");
        System.out.println("0: Volver al menu principal");
    }
    
    @Override
    public void setControlador(ControlLectura le){
        this.controladorL=le;
    }
    
    @Override
    public void setControlador(ControlEscritura e) {
       this.controladorE=e;
    }
    

    @Override
    public void arranca() {
        procesaNuevaOperacion();
    }

    @Override
    public String getPath() {
        System.out.println("Introduce el archivo:");
        return leeString();
    }
    
    @Override
    public char getChar() {
        System.out.println("Introduce el caracter:");
        String str = leeString();
        return str.charAt(0);
    }
    
    @Override
    public String getEscribir(){
        System.out.println("¿Que desea escribir?");
        return leeString();
    }
}
