/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.aem.existdb;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultItem;
import javax.xml.xquery.XQResultSequence;
import net.xqj.exist.ExistXQDataSource;


/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 */
public class ExistDb {

    
    // Variables para la conexion a la base de datos
    private static XQDataSource server;
    private static XQConnection conn;
    
    public static void main(String[] args) {
        
        conecta();
        
        //consulta("/EMPLEADOS");
        
        //modificar("update rename /EMPLEADOS/EMP_ROW as 'fila_emple'");
        
        
        /*
        ====================
        ==== EJERCICIO1 ====
        ====================
        */
        
        String nuevoEmpleado = "<empleado>" +
                "<nombre>PEDRO FRAILE</nombre>" +
                "<puesto>Tecnico</puesto>" +
                "<salario>2340</salario>" +
                "</empleado>";
        
        
        String insertEj1 = "update insert " + nuevoEmpleado + " into /universidad/departamento[2]";
        
        //modificar(insertEj1);
        
        String modificarEj1="update replace /universiad/departamento/";        
        //modificar("update ")
        
        desconecta();
        
    }
    
    
    private static void conecta(){
        
        try {
            
            server = new ExistXQDataSource();
            server.setProperty("serverName", "localhost");
            server.setProperty("port", "8080");
            server.setProperty("user", "ejemplo");
            server.setProperty("password", "ejemplo");
            
            conn = server.getConnection();
            System.out.println("[Traza] Conexion establecida con la base de datos");
            
        } catch (XQException ex) {
            Logger.getLogger(ExistDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    private static void desconecta(){
    
        try {
            conn.close();
            System.out.println("[Traza] Conexion finalizada con base de datos");
        } catch (XQException ex) {
            Logger.getLogger(ExistDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static void consulta(String textoConsulta){
    
        try {
            XQPreparedExpression consulta;
            XQResultSequence resultado;
            
            consulta = conn.prepareExpression(textoConsulta);
            
            resultado = consulta.executeQuery();
            
            XQResultItem r_item;
            
            while(resultado.next()){
                
                r_item = (XQResultItem) resultado.getItem();
                
                
                System.out.println("Elemento: " + r_item.getItemAsString(null).replaceAll("xmlns=\"[^\"]*\"", ""));
                
            }
        } catch (XQException ex) {
            Logger.getLogger(ExistDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static void modificar(String textoDML){
        
        try {
            XQExpression expresion;
            expresion = conn.createExpression();
            expresion.executeCommand(textoDML);
            
            System.out.println("[Traza] Cambios realizados correctamente");
            
        } catch (XQException ex) {
            Logger.getLogger(ExistDb.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("[Traza] Error al realizar los cambios");
        }
        
        
        
    }
    
    
    
}
