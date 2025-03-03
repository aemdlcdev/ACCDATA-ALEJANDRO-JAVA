/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.accesoaleatorio.controlador;

import com.aem.accesoaleatorio.modelo.Empleado;
import com.aem.accesoaleatorio.modelo.Escritura;
import com.aem.accesoaleatorio.modelo.FicheroEmpleados;
import com.aem.accesoaleatorio.modelo.Lectura;
import com.aem.accesoaleatorio.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * Created on 15 oct 2024
 * @version 1.0
 */
public class Controlador implements ActionListener{
    
    // Vista y modelo
    private final InterfazVista vista;
    private Escritura modeloE;
    private Lectura modeloL;
    private Empleado empleado;
    // Controlador
    public Controlador(InterfazVista vista, Escritura modeloE, Lectura modeloL) {
        this.vista = vista;
        this.modeloE = modeloE;
        this.modeloL = modeloL;
        this.vista.setrControlador(this);
    }
    
    // Action Performed
    @Override
    public void actionPerformed(ActionEvent evento) {
        
        switch (evento.getActionCommand()){
            case InterfazVista.LECTURA_EMPLEADO -> {
                this.modeloL = new Lectura(vista.getRuta());
                modeloL.lecturaEmpleado(vista.getIdentificador());
            }
            case InterfazVista.MUESTRA_REGISTROS -> {
                this.modeloL = new Lectura(vista.getRuta());
                modeloL.muestraRegistros();
            }
            case InterfazVista.ESCRIBIR_EMPLEADO_FINAL_ARCHIVO -> {
                modeloE = new Escritura(vista.getRuta());
                empleado = new Empleado (vista.getApellido(),vista.getDepartamento(),vista.getSalario());
                modeloE.escribirEmpleadoFinalArchivo(empleado);
            }
            case InterfazVista.ESCRIBIR_EMPLEADO_POR_IDENTIFICADOR -> {
                modeloE = new Escritura(vista.getRuta());
                empleado = new Empleado(vista.getIdentificadorL(),vista.getApellido(),vista.getDepartamento(),vista.getSalario());
//                modeloE.escribirEmpleadoPorIdentificador(empleado); // Tendriamos que pasarle los campo correspondientes, ya que ya no pasamos el objeto Empleado
            }
            case InterfazVista.BORRA_REGISTRO -> {
                modeloE = new Escritura(vista.getRuta());
                modeloE.borraResgistro(vista.getIdentificadorL());
            }
            case InterfazVista.MODIFICA_APELLIDO -> {
                modeloE = new Escritura(vista.getRuta());
                modeloE.modificarApellido(vista.getIdentificadorL(), vista.getApellido());
            }
        }
        
    }

}
