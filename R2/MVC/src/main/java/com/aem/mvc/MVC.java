/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.aem.mvc;

import com.aem.mvc.controlador.ControlConversor;
import com.aem.mvc.vista.VentanaConversor;
import com.aem.mvc.modelo.ConversorEurosPesetas;
import com.aem.mvc.vista.InterfazVista;
import com.aem.mvc.vista.VentanaConversorTexto;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * Created on 15 sept 2024
 * @version 1.0
 */
public class MVC {

    public static void main(String[] args) {
        InterfazVista vista = new VentanaConversorTexto();
        ConversorEurosPesetas modelo = new ConversorEurosPesetas();
        ControlConversor control = new ControlConversor(vista, modelo);
    }
}

