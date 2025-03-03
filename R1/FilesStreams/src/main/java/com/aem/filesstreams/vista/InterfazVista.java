/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.aem.filesstreams.vista;

import com.aem.filesstreams.controlador.ControlEscritura;
import com.aem.filesstreams.controlador.ControlLectura;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 2 oct 2024
 */
public interface InterfazVista {
    
    //Contantes definidas para la lectura
    String LEE_CHAR_UNO_A_UNO_BUFFERED = "Leer caracteres uno a uno usando StringBuffer(50)";
    String LEE_CHAR_ARRAY_5 = "Leer caracteres usando un array(5)";
    String LEE_LINEAS_BUFFERED_READER = "Leer lineas usando BufferedReader";
    
    //Contantes definidas para la escritura
    String ESCRIBE_CHAR_A_CHAR = "Escribir o sobreescribir caracter a caracter";
    String ESCRIBE_CHAR_ARRAY = "Escribir o sobreescribir usando array de caracteres";
    String ESCRIBRE_BUFFERED = "Escribir o sobreescribir usando BufferedWriter";
    String ESCRIBE_PRINTWRITER= "Escribir o sobreescribir usando PrintWriter";
    String ENCRIPTAR = "Encriptar un archivo";
    String DESENCRIPTAR = "Desencriptar un archivo";
    //Metodos void
    void setControlador(ControlLectura l);
    void setControlador(ControlEscritura e);
    void arranca();
    
    char getChar();
    String getEscribir();
    //boolean getSobreescribir();
    
    //Metodos 
    String getPath();
    
}
