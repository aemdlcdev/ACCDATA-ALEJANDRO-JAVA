/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.ejercicio3examen.vista;

import com.aem.ejercicio3examen.controlador.Controlador;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 13 nov 2024
 *
 */
public class VistaTexto implements InterfazVista{

    private Controlador controlador;
    
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
        System.out.println("1: Añadir producto");
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
            case 1 -> controlador.actionPerformed(new ActionEvent(this, operacion, INSERTAR_PRODUCTO));
            
            default -> operacionIncorrecta();
        }
        procesaNuevaOperacion();
    }
    
    private void operacionIncorrecta() {
        System.out.print("Operacion incorrecta. ");
        procesaNuevaOperacion();
    }
    
    
    
    @Override
    public void setControlador(Controlador c) {
        this.controlador = c;
    }

    @Override
    public void arranca() {
        procesaNuevaOperacion();
    }

    @Override
    public String getNombre() {
        System.out.println("Introduce el nombre del producto:");
        return leeString();
    }

    @Override
    public String getSuper() {
        System.out.println("Introduce el supermercado donde quiere comprar: ");
        return leeString();
    }

    @Override
    public double getCantidad() {
        System.out.println("Indique la cantidad a comprar: ");
        return (double) leeOpcion();
    }

}
