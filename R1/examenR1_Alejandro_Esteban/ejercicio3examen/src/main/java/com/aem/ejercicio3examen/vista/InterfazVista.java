/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.aem.ejercicio3examen.vista;

import com.aem.ejercicio3examen.controlador.Controlador;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 13 nov 2024
 */
public interface InterfazVista {

    void setControlador(Controlador c);
    void arranca();
    
    String getNombre();
    String getSuper();
    double getCantidad();
    
    String INSERTAR_PRODUCTO = "Inserta un producto en la lista";
    
}
