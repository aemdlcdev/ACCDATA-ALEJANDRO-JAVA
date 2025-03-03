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
public class Conversor {
    
    private final double cambio;
    
    public Conversor(double valorCambio){
        this.cambio=valorCambio;
    }
    
    /**
     * Metodo que convierte de euros a una moneda de destino
     * @param cantidad en euros que se desea convertir
     * @return cantidad de la moneda de destino convertida desde euros
     */
    
    public double eurosAmoneda(double cantidad, int comision){
        double comi=(comision*(cantidad*this.cambio))/100;
        return (cantidad*this.cambio)-(comi);
    }
    
    /**
     * Metodo que convierte de una moneda a euros
     * @param cantidad de la moneda que se quiere convertir en euros
     * @return cantidad en euros convertidos desde la moneda deseada
     */
    
    public double monedaAeuros(double cantidad, int comision){
        double comi=(comision*(cantidad/this.cambio))/100;
        return (cantidad/this.cambio)-(comi);
    }
    
}
