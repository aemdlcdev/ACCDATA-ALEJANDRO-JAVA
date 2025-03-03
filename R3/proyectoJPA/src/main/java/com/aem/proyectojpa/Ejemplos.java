/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aem.proyectojpa;

import static com.aem.proyectojpa.ProyectoJPA.emfactory;
import static com.aem.proyectojpa.ProyectoJPA.entityManager;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Alejandro
 */
public class Ejemplos {
    
    static EntityManagerFactory emfactory;
    static EntityManager entityManager;
    
    public static void main (String[] args){
        
        inicializaFactory();
        
        // Consulta simple
        Query query = entityManager.createQuery("SELECT UPPER(d.dnombre) from Departamentos d");
        
        List<String> list = query.getResultList();
        
        for (String str: list){
            System.out.println("Dept name: " + str);
        }
        
        System.out.println("==============================");
        // Consulta varios campos
        System.out.println("==============================");
        TypedQuery<Object []> queryV = entityManager.createQuery("Select d.dnombre, d.loc from Departamentos d", Object[].class);
        
        List <Object[]> listaV = queryV.getResultList();
        
        for (Object[] obj:listaV){
            System.out.println("Dept name: " + obj[0]);
            
            System.out.println("Dept loc: " + obj[1]);
            
            System.out.println("==============================");
        }
        
        System.out.println("==============================");
        // Consulta almacenada
        System.out.println("==============================");
        
        Query queryA = entityManager.createNamedQuery("Departamentos.findAll");
        List<Departamentos> listaDepartamentos = queryA.getResultList();
        
        for(Departamentos d: listaDepartamentos){
            System.out.println("Dept name: " + d.getDnombre());
        }
        
        System.out.println("==============================");
        // Consulta almacenada con parametros
        System.out.println("==============================");
        
        Query queryAconParametro = entityManager.createNamedQuery("Departamentos.findByDeptNo");
        
        queryAconParametro.setParameter("deptNo", 2);
        
        List <Departamentos> listDept = queryAconParametro.getResultList();
        
        for (Departamentos d: listDept){
            System.out.println("Dept name: " + d.getDnombre());
        }
        
        //==============================
        //============Update ===========          
        //==============================
        // Iniciamos una transaccion
        
        entityManager.close();
        emfactory.close();
        
    }
    
    public static void inicializaFactory(){
    
        emfactory = Persistence.createEntityManagerFactory("com.aem_proyectoJPA_jar_1.0-SNAPSHOTPU");
        entityManager = emfactory.createEntityManager();
        
    }
}
