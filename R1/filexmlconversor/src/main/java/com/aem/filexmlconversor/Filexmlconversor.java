/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.aem.filexmlconversor;

import com.aem.filexmlconversor.modelo.Conversor;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 */
public class Filexmlconversor {

    public static void main(String[] args) {
        
        Conversor modelo = new Conversor("./recursos/alumnos.xml",
                                        "./recursos/alumnosPlantilla.xsl", 
                                        "./recursos/alumnos.html");
        
       modelo.convertirAHTML();
    }
}
