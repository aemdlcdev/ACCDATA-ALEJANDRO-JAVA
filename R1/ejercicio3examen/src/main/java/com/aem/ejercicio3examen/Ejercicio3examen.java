/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.aem.ejercicio3examen;

import com.aem.ejercicio3examen.controlador.Controlador;
import com.aem.ejercicio3examen.modelo.GestionContenidoDom;
import com.aem.ejercicio3examen.vista.InterfazVista;
import com.aem.ejercicio3examen.vista.VistaTexto;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 */
public class Ejercicio3examen {

    public static void main(String[] args) {
        
        GestionContenidoDom modelo = new GestionContenidoDom("productos");
        
        InterfazVista vista = new VistaTexto();
        
        Controlador controlador = new Controlador(vista, modelo);
        
        vista.arranca();
        
        
    }
}
