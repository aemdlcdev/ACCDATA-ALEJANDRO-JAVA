/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package filestreambytes.vista;

import filestreambytes.controlador.Controlador;
import java.util.ResourceBundle.Control;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 7 oct 2024
 */
public interface InterfazVista {
    
    //Constantes
    String LEE_DATO="LEE UN DATO SIMPLE";
    String LEE_OBJETO="LEE UN OBJETO";
    String ESCRIBE_DATO="ESCRIBE UN DATO SIMPLE";
    String ESCRIBE_OBJETO="ESCRIBE UN OBJETO";
    
    //Metodos void
    void arranca();
    void setControlador(Controlador c);
    
    //Metodos
    String getRuta();
    Object getObjeto();
    String getNombre();
    int getEdad();
    double getSalario();
}
