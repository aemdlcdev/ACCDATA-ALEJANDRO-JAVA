/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package filestreambytes.vista;

import filestreambytes.controlador.Controlador;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle.Control;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 7 oct 2024
 *
 */
public class VistaTexto implements InterfazVista{
    
    private Controlador controlador;
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
    
    private int leeOperacion(){
        String s = null;
        try{
            s = in.readLine();
            return Integer.parseInt(s);
        } catch (IOException ex) {
            Logger.getLogger(VistaTexto.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    private double leeDouble(){
        String s = null;
        try{
            s = in.readLine();
            return Double.parseDouble(s);
        } catch (IOException ex) {
            Logger.getLogger(VistaTexto.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public void muestraOpciones(){
        System.out.println("Selecciona una operacion.");
        System.out.println("1: Leer dato simple");
        System.out.println("2: Leer objeto");
        System.out.println("3: Escribir dato");    
        System.out.println("4: Escribir objeto Empleado");
        System.out.println("0: Salir");
    }
    
    public void procesaOperacion(){
        int operacion;
        muestraOpciones();
        operacion=leeOperacion();
        switch (operacion){
            case 1 ->{
                controlador.actionPerformed(new ActionEvent(this, operacion, LEE_DATO));
            }
            case 2 ->{
                controlador.actionPerformed(new ActionEvent(this, operacion, LEE_OBJETO));
            }
            case 3 ->{
                controlador.actionPerformed(new ActionEvent(this, operacion, ESCRIBE_DATO));
            }
            case 4 ->{
                controlador.actionPerformed(new ActionEvent(this, operacion, ESCRIBE_OBJETO));
            }
            case 0 ->{
                System.exit(0);
            }
            default -> {
                operacionIncorrecta();
            }
        }
        procesaOperacion();
    }
    
    private void operacionIncorrecta(){
        System.out.println("Operacion incocrrecta!");
        procesaOperacion();
    }
    
    @Override
    public void arranca() {
        procesaOperacion();
    }

    
    @Override 
    public String getRuta(){
        System.out.println("Introduce la ruta:");
        return leeString();
    }
    
    @Override
    public Object getObjeto(){ //revisar
        System.out.println("introduce el nombre del objeto");
        return leeString();
    }

    @Override
    public void setControlador(Controlador c) {
        this.controlador=c;
    }

    @Override
    public String getNombre() {
        System.out.println("Introduce el nombre:");
        return leeString();
    }

    @Override
    public int getEdad() {
        System.out.println("Introduce la edad");
        return leeOperacion();
    }

    @Override
    public double getSalario() {
        System.out.println("Introduce el salario");
        return leeDouble();
    }
}
