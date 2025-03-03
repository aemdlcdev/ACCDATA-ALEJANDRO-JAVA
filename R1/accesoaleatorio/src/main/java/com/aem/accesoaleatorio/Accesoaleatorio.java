/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.aem.accesoaleatorio;

import com.aem.accesoaleatorio.controlador.Controlador;
import com.aem.accesoaleatorio.modelo.Empleado;
import com.aem.accesoaleatorio.modelo.Escritura;
import com.aem.accesoaleatorio.modelo.Lectura;
import com.aem.accesoaleatorio.vista.InterfazVista;
import com.aem.accesoaleatorio.vista.VistaTexto;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 */
public class Accesoaleatorio{

    public static void main(String[] args) {
       
        InterfazVista vista = new VistaTexto();
        Escritura modeloE = new Escritura("");
        Lectura modeloL = new Lectura("");
        Controlador c = new Controlador(vista, modeloE, modeloL);
        
        vista.arranca();
    }
}
