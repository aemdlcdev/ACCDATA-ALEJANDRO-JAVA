/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.aem.ejercicio2examen;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 */
public class Ejercicio2examen {

    public static void main(String[] args) {
        
        
        Modelo m = new Modelo("./ORIGEN/datosReformas.dat");
        Reforma reforma = null;
        
        m.muestraEjercicio2(4);
        
        reforma = new Reforma(4,"Renovar fontaneria","Calle Toledo 47 2A",700.00);
        m.insertarReforma(reforma);
        m.muestraEjercicio2(4);
        
    }
}
