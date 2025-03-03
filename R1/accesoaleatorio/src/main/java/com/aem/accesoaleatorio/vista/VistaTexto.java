/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.accesoaleatorio.vista;

import com.aem.accesoaleatorio.controlador.Controlador;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * Created on 15 oct 2024
 * @version 1.0
 */
public class VistaTexto implements InterfazVista {
    
    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private Controlador controlador;
    
    // Métodos para leer entradas por teclado
    public String leeString(){
        try{
            String s = in.readLine();
            return s;
        } catch(IOException ex){
            System.out.println("Error en la cadena introducida" + ex.getMessage());
            return leeString();
        }
    }
    
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
    
    private double leeDouble(){
        String s=null;
        try{
            s = in.readLine();
            return Double.parseDouble(s);
        } catch (IOException | NumberFormatException ex){
            operacionIncorrecta();
            return 0;
        }
    }
    
    private long leeLong(){
        String s = null;
        try{
            s = in.readLine();
            return Long.parseLong(s);
        } catch (IOException | NumberFormatException ex){
            operacionIncorrecta();
            return 0L;
        }
    }
    
    
    private void procesaNuevaOperacion(){
        int operacion;
        solicitarOperacion();
        operacion = leeOpcion();
        
        switch (operacion){
            
            case 1 ->{
                controlador.actionPerformed(new ActionEvent(this, operacion,LECTURA_EMPLEADO));
            }
            case 2 ->{
                controlador.actionPerformed(new ActionEvent(this,operacion,MUESTRA_REGISTROS));
            }
            case 3 ->{
                controlador.actionPerformed(new ActionEvent(this,operacion,ESCRIBIR_EMPLEADO_FINAL_ARCHIVO));
            } 
            case 4 ->{
                controlador.actionPerformed(new ActionEvent(this,operacion,ESCRIBIR_EMPLEADO_POR_IDENTIFICADOR));
            } 
            case 5 ->{
                controlador.actionPerformed(new ActionEvent(this,operacion,BORRA_REGISTRO));
            } 
            case 6 ->{
                controlador.actionPerformed(new ActionEvent(this,operacion,MODIFICA_APELLIDO));
            } 
            case 0 ->{
                System.out.println("Adios!");
                System.exit(0);
            } 
            
            default -> {
                System.out.println("Operacion no reconocida!");
            }
        }
        procesaNuevaOperacion();
    }
    
    
    private void solicitarOperacion(){
        System.out.println("1. Leer empleado por identificador");
        System.out.println("2. Mostrar todos los registro del archivo");
        System.out.println("3. Escribir empleado al final del archivo");
        System.out.println("4. Escribir empleado por identificador");
        System.out.println("5. Borrar registro");
        System.out.println("6. Modificar apellido");
        System.out.println("0. Salir");
    }
    
    private void operacionIncorrecta(){
        System.out.println("Operacion incorrecta!");
        procesaNuevaOperacion();
    }

    @Override
    public void setrControlador(Controlador c) {
        this.controlador = c;
    }

    @Override
    public void arranca() {
        procesaNuevaOperacion();
    }

    @Override
    public String getRuta() {
        System.out.println("Introduce el archivo:");
        return leeString();
    }

    @Override
    public int getIdentificador() {
        System.out.println("Introduce el identificador:");
        return leeOpcion();
    }

    @Override
    public long getIdentificadorL() {
        System.out.println("Introduce el identificador (long):");
        return leeLong();
    }

    @Override
    public double getSalario() {
        System.out.println("Introduce el salario (0.0):");
        return leeDouble();
    }
    
    @Override
    public String getApellido(){
        System.out.println("Introduce el apellido:");
        return leeString();
    }
    
    @Override
    public int getDepartamento(){
        System.out.println("Introduce el departamento:");
        return leeOpcion();
    }
    
}
