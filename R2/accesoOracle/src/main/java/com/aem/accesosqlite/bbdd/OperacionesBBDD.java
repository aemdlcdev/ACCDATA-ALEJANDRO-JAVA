/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.ejercicio2examen.modelo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 27 nov 2024
 *
 */
public class OperacionesBBDD {
    
    private final String driver;
    private final String urlconnection;
    private Properties propiedades = null;
    
    private Connection conexion;
    private PreparedStatement preparedStatement;
    
    private static DatabaseMetaData dbmd;
    
    private static OperacionesBBDD operacionesBBDD = null;
        
    public OperacionesBBDD(){
        driver = "oracle.jdbc.driver.OracleDriver";
        urlconnection="jdbc:oracle:thin:@localhost:1521";
    }

    public Connection getConexion() {
        return conexion;
    }
      
    /**
     * Devuelve una instancia de la clase. Sólo una. Patrón Singleton
     * 
     * @return La instancia de la clase
     */
    public static OperacionesBBDD getInstance(){
        if (operacionesBBDD == null){
            operacionesBBDD = new OperacionesBBDD();
        }
        return operacionesBBDD;
    }
    
    /**
     * Realiza la conexión a la BBDD
     */
    public void abrirConexion(){
        try {
            
            this.propiedades = new Properties();
            this.propiedades.setProperty("user", "ejemplo");
            this.propiedades.setProperty("password", "ejemplo");
            this.propiedades.setProperty("bbdd", "free");
  
            Class.forName(driver);
            this.conexion = DriverManager.getConnection(urlconnection, propiedades);
            
            // Para obtener información de la conexión
            dbmd = conexion.getMetaData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OperacionesBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Cierra la conexión a la bbdd
     */
    public void cerrarConexion(){
        try {
            this.conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
