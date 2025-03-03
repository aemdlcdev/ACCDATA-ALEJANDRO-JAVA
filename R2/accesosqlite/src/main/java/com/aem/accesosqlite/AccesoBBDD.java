/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.aem.accesosqlite;

import com.aem.accesosqlite.bbdd.OperacionesBBDD;
import com.aem.accesosqlite.modelo.Departamento;
import com.aem.accesosqlite.modelo.Empleado;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 */
public class AccesoBBDD {
    private static OperacionesBBDD bbdd = OperacionesBBDD.getInstance();
    
    public static void main(String[] args) {
        
//        Departamento departamento = null;
//        
//        
//        
//        
//        
//        departamento = new Departamento();
//        departamento.selectById(bbdd, 1);
//        
//        departamento = new Departamento(2,"Consumo","Huelva");
//        
//        departamento.update(bbdd);
//        
//        departamento = new Departamento(3,"Administracion","Toledo");
//        departamento.update(bbdd);
//        
//        System.out.println(departamento);
//        
//        Departamento.delete(bbdd, 4);
//        
//        Departamento.mostrarResultSet(Departamento.selectAll(bbdd));
        
//        departamento = new Departamento(1,"Informática","Ciudad Real");
//        departamento.insertarDatosDepartamento();
//        
//        departamento = new Departamento(2,"Administración","Ciudad Real");   
//        departamento.insertarDatosDepartamento();
//        
//        departamento = new Departamento(3,"Consumo","Ciudad Real");
//        departamento.insertarDatosDepartamento();
//        
//        departamento = new Departamento(4,"Recursos Humanos", "Madrid");
//        departamento.insertarDatosDepartamento();
            

//          empleado = new Empleado(1,"Esteban","Programador","C/Ejemplo","04-12-2024",1600.00,20.00,1);  
//          empleado.insertarDatosEmpleado();
          


        bbdd.abrirConexion();
        
        Empleado empleado = null;
        empleado = new Empleado(5,"Fernandez","Programador","C/Ejemplo01323","14-12-2022",1200.00,15.00,1);  
        //empleado.insertarDatosEmpleado();
        
        empleado = new Empleado(1,"Esteban","Programador","C/Ejemplo", "04-12-2024", 3000.00,30.00,1);
        
        empleado.update(bbdd);
        
        Empleado.mostrarResultSet(Empleado.selectAll(bbdd));
        
        
        bbdd.cerrarConexion();
        
    }

}
