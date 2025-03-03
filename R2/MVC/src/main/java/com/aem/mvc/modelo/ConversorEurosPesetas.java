/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.mvc.modelo;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * Created on 15 sept 2024
 * @version 1.0
 */
public class ConversorEurosPesetas extends Conversor {
    
    public ConversorEurosPesetas() {
        super(166.386);
    }
    
    public double eurosApesetas(double cantidad,int comision){
        return eurosAmoneda(cantidad,comision);
    }
    
    public double pesetasAeuros(double cantidad,int comision){
        return monedaAeuros(cantidad,comision);
    }
    
}
