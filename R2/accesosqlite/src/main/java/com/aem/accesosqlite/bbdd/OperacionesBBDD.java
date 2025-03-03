/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.accesosqlite.bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 4 nov 2024
 *
 */
public class OperacionesBBDD {
    
    private final String driver;
    private final String urlconnection;
    
    private static OperacionesBBDD operacionesBBDD;
    
    private Connection conexion;
    private PreparedStatement preparedStatement;
    
    private OperacionesBBDD(){
        driver = "org.sqlite.JDBC";
        urlconnection = "jdbc:sqlite:./bbdd/ejemplo.db";
    }
    
    public static OperacionesBBDD getInstance(){
        if(operacionesBBDD == null){
            operacionesBBDD = new OperacionesBBDD();
        }
        return operacionesBBDD;
    }
    
    public void abrirConexion(){
        try {
            Class.forName(driver);
            this.conexion = DriverManager.getConnection(urlconnection);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OperacionesBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarConexion(){
        try {
            this.conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Optional<ResultSet> insert(String insertSQL, Object... params) throws SQLException{
        
        preparedStatement = conexion.prepareStatement(insertSQL,PreparedStatement.RETURN_GENERATED_KEYS);
        
        //"insert into Departamentos values (?,?,?)", 1,"Informática","Ciudad Real"
        //                                   1,2,3
        // params = [1,"Informática","Ciudad Real]
        //           0,      1       ,       2
        
        for (int i = 0; i < params.length; i++){
            preparedStatement.setObject(i+1, params[i]);
        }
        
        // Ya lo tenemos construido
        // "insert into Departamentos values (?,?,?)", 1,"Informática","Ciudad Real"
        
        preparedStatement.executeUpdate();
        
        return Optional.of(preparedStatement.getGeneratedKeys());
        
    }
    
    private ResultSet executeQuery(String querySQL, Object... params) throws SQLException{
        
        preparedStatement = conexion.prepareStatement(querySQL);
        
        for (int i = 0; i < params.length; i++){
            preparedStatement.setObject(i+1, params[i]);
        }
        
        return preparedStatement.executeQuery();
        
    }
    
    public Optional<ResultSet> select(String querySQL, Object... params) throws SQLException{
        return Optional.of(executeQuery(querySQL,params));
    }
    
    public int updateQuery(String genericSQL, Object... params) throws SQLException{
        preparedStatement = conexion.prepareStatement(genericSQL);

        for (int i = 0; i < params.length; i++){
            preparedStatement.setObject(i+1, params[i]);
        }
        
        return preparedStatement.executeUpdate();
    }
    
    public int update (String genericSQL, Object... params) throws SQLException{
        return updateQuery(genericSQL, params);
    }
    
    public int delete (String genericSQL, Object... params) throws SQLException{
        return updateQuery(genericSQL, params);
    }
    
}
