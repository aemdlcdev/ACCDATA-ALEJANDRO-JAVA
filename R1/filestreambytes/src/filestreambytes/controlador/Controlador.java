/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package filestreambytes.controlador;

import filestreambytes.modelo.Escritura;
import filestreambytes.modelo.Lectura;
import filestreambytes.modelo.objetos.Empleado;
import filestreambytes.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 7 oct 2024
 *
 */
public class Controlador implements ActionListener {
    
    private final InterfazVista vista;
    private Escritura modeloE;
    private Lectura modeloL;
    
    public Controlador(InterfazVista vista, Escritura modeloE, Lectura modeloL){
        this.vista=vista;
        this.modeloE=modeloE;
        this.modeloL= modeloL;
        this.vista.setControlador(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        String rutaL="";
        
        String rutaE = "";
        switch (evento.getActionCommand()){
            case InterfazVista.LEE_DATO -> {
                rutaL = vista.getRuta();
                modeloL.setRuta(rutaL);
                modeloL.lecturaDatosSimple();
            }
            
            case InterfazVista.LEE_OBJETO -> {
                rutaL = vista.getRuta();
                modeloL.setRuta(rutaL);
                modeloL.lecturaObjetos();
            }
            
            case InterfazVista.ESCRIBE_DATO -> {
                rutaE=vista.getRuta();
                modeloE.setRuta(rutaE);
                modeloE.escribirDatosSimples();
            }
            
            case InterfazVista.ESCRIBE_OBJETO-> {
                rutaE=vista.getRuta();
                modeloE.setRuta(rutaE);
                Empleado emple = new Empleado();
                emple.setNombre(vista.getNombre());
                emple.setEdad(vista.getEdad());
                emple.setSalario(vista.getSalario());
                modeloE.escribirObjetos(emple);
            }
            
        }
    }
    
}
