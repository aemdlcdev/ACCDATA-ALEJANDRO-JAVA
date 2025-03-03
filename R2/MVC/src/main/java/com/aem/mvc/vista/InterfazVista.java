/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aem.mvc.vista;

import com.aem.mvc.controlador.ControlConversor;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 */
public interface InterfazVista {
    
    void setControlador(ControlConversor c);
    void arranca();
    double getCantidad();
    void escribeCambio(String s);
    
    static final String AEUROS ="Pesetas a Euros";
    static final String APESETAS ="Euros a Pesetas";

    public int getComision();
}
