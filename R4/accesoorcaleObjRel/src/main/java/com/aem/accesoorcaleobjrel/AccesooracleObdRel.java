/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.aem.accesoorcaleobjrel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 */


// hacer pdf casa y hacer borrado y modificacion
public class AccesooracleObdRel {

    
    private static String driver;
    private static Connection conexion;
    
    public static void main(String[] args) {
        abrirConexion();
        
        
        insertarPrepared();
        realizaConsulta();        
        cerrarConexion();
        
    }
    
    public static void abrirConexion(){
        try {
            driver ="oracle.jdbc.driver.OracleDriver";
            String urlConnection="jdbc:oracle:thin:@localhost:1521/FREE";
            
            Properties propiedades = new Properties();
            
            propiedades.setProperty("user", "dam2");
            propiedades.setProperty("password", "dam2");
  
            Class.forName(driver);
            conexion = DriverManager.getConnection(urlConnection, propiedades);
            
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AccesooracleObdRel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void cerrarConexion(){
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccesooracleObdRel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void insertarPrepared(){
        try {
            String sql = "INSERT INTO alumnos VALUES ("
                    + "new Persona("
                    + "?,"
                    + "?,"
                    + "new Direccion(?,?,?),"
                    + "?"
                    + "))";
            
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            
            java.sql.Date fecha = Date.valueOf("2023-12-13");
            
            sentencia.setInt(1, 67);
            sentencia.setString(2, "Marta Sánchez");
            
            sentencia.setString(3, "Calatrava"); // Objeto direccion
            sentencia.setString(4, "Ciudad Real"); // Objeto direccion
            sentencia.setInt(5,13003); // Objeto direccion
            
            sentencia.setDate(6, fecha);
            
            sentencia.executeUpdate();
            sentencia.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AccesooracleObdRel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void modificarPrepared() {
        try {
            String sql = "UPDATE alumnos SET nombre = ?, direc = new Direccion(?,?,?), fecha_nacimiento = ? WHERE codigo = ?";

            PreparedStatement sentencia = conexion.prepareStatement(sql);

            java.sql.Date fecha = Date.valueOf("2023-12-13");

            sentencia.setString(1, "Ana Pérez");

            sentencia.setString(2, "Gran Vía"); // Objeto direccion
            sentencia.setString(3, "Madrid"); // Objeto direccion
            sentencia.setInt(4, 28013); // Objeto direccion

            sentencia.setDate(5, fecha);
            sentencia.setInt(6, 67);

            sentencia.executeUpdate();
            sentencia.close();

        } catch (SQLException ex) {
            Logger.getLogger(AccesooracleObdRel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void borrarPrepared() {
        try {
            String sql = "DELETE FROM alumnos WHERE codigo = ?";

            PreparedStatement sentencia = conexion.prepareStatement(sql);

            sentencia.setInt(1, 67);

            sentencia.executeUpdate();
            sentencia.close();

        } catch (SQLException ex) {
            Logger.getLogger(AccesooracleObdRel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void realizaConsulta(){
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt= conexion.createStatement();
            
            rs = stmt.executeQuery("select codigo,nombre, direc from alumnos");
            
            while(rs.next()){
                String codigo = rs.getString(1);
                String nombre = rs.getString(2);
                
                java.sql.Struct objeto = (java.sql.Struct) rs.getObject(3);
                
                String ciudad="";
                
                if(objeto !=null){
                    Object[] atributos = objeto.getAttributes();
                    
                    ciudad = (String) atributos[1];
                }
                System.out.println("Código: " + codigo + " Nombre: "+ nombre+ " Dirección:" + ciudad);
                
            }    
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AccesooracleObdRel.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccesooracleObdRel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccesooracleObdRel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
