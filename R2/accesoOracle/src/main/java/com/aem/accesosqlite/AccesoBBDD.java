/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.aem.accesosqlite;

import com.aem.accesosqlite.bbdd.OperacionesBBDD;
import com.aem.accesosqlite.modelo.Departamento;
import com.aem.accesosqlite.modelo.Empleado;


/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 */
public class AccesoBBDD {
    private static OperacionesBBDD bbdd = OperacionesBBDD.getInstance();
    
    public static void main(String[] args) {
        
        Departamento departamento = new Departamento(4,"Administracion","Toledo");
        
        Empleado empleado = new Empleado ();
        
        bbdd.abrirConexion();
     
           empleado.insertarDatosEmpleado(3, "Moron", "VENDEDOR", 7723, 1700, 30, 20);
//         Empleado.selectMaxSalario(bbdd);
//         
//         Empleado.updateSalario(bbdd);
//         
//         Empleado.updateSalarioPorcentaje(bbdd, 20, 50);
//         
         
        
//        System.out.println(Departamento.pNombreDepart(bbdd, 2));
//        
//        System.out.println(Departamento.fNombreDepart(bbdd, 2));
        
//        bbdd.obtenerInformacionDeConexion();
//        System.out.println("");
//        bbdd.obtenerInformacionDeLasColumnas("departamentos");
//        System.out.println("");
//        bbdd.obtenerInformacionDeLasTablas();
//        System.out.println("");
//        bbdd.obtenerInformacionDelResultSet(Departamento.selectAll(bbdd));
//        System.out.println("");
//        bbdd.obtenerInformacionOperacionesResultSet();
//        System.out.println("");
//        bbdd.obtenerNumeroFilasDevueltas(Departamento.selectAll(bbdd));
//        
        bbdd.cerrarConexion();
        
    }

}
