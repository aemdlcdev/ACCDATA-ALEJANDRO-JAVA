/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.accesosqlite.modelo;

import com.aem.accesosqlite.AccesoBBDD;
import com.aem.accesosqlite.bbdd.OperacionesBBDD;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
public class Departamento {
    private int dept_no;
    private String dnombre;
    private String loc;

    public Departamento() {
    }

    public Departamento(int dept_no, String dnombre, String loc) {
        this.dept_no = dept_no;
        this.dnombre = dnombre;
        this.loc = loc;
    }

    public int getDept_no() {
        return dept_no;
    }

    public String getDnombre() {
        return dnombre;
    }

    public String getLoc() {
        return loc;
    }

    public void setDept_no(int dept_no) {
        this.dept_no = dept_no;
    }

    public void setDnombre(String dnombre) {
        this.dnombre = dnombre;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "Departamento{" + "dept_no=" + dept_no + ", dnombre=" + dnombre + ", loc=" + loc + '}';
    }
    
    
    /**************************************************************************
     * EJECUCIÓN DE SENTENCIAS DE MANIPULACIÓN DE DATOS
     **************************************************************************/
    
    /**
     * Inserción de un departamento
     * 
     * @param bbdd Clase para las operaciones con la bbdd
     */
    public void insertar(OperacionesBBDD bbdd){
        try {
            bbdd.insert("insert into Departamentos values (?,?,?)", this.dept_no, this.dnombre, this.loc);
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Selecciona todos los registros de la tabla
     * 
     * @param bbdd Clase para las operaciones con la bbdd
     * @return Registros devueltos
     */
    public static Optional<ResultSet> selectAll(OperacionesBBDD bbdd){
        Optional<ResultSet> rs = null;
        try {
            rs = bbdd.select("SELECT d.* FROM departamentos d");   // Optional permite que la variable tenga valor o nulo
                                                                   // Usamos un alias para poder hacer modificaciones sobre el ResultSet
            
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    /**
     * Muestra los datos del ResultSet
     * 
     * @param rs ResultSet del cual queremos mostrar los datos
     */
    public static void mostrarResultSet(Optional<ResultSet> rs){
        try {
            if (rs.isPresent()) {
                while (rs.get().next()) {
                    System.out.println("Número departamento:"+ rs.get().getInt("dept_no") + " Nombre:" + rs.get().getString("dnombre") + 
                            " Localidad:" + rs.get().getString("loc"));
                }
            }       
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Selecciona un registro filtrando por la clave primaria
     * 
     * @param bbdd Clase para las operaciones con la bbdd
     * @param dept_no Número del departamento del cual queremos seleccionar la información 
     */
    public void selectById(OperacionesBBDD bbdd, int dept_no){
        try {
            Optional<ResultSet> rs = bbdd.select("SELECT d.* FROM departamentos d WHERE d.dept_no = ?", dept_no);
                                                 // Usamos un alias para poder hacer modificaciones sobre el ResultSet

            if (rs.isPresent()) {
                while (rs.get().next()) {
                    this.setDept_no(rs.get().getInt("dept_no"));
                    this.setDnombre(rs.get().getString("dnombre")); 
                    this.setLoc(rs.get().getString("loc"));
                }
            }         
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    /**
     * Modifica un departamento
     * 
     * @param bbdd Clase para las operaciones con la bbdd
     */
    public void update(OperacionesBBDD bbdd){
        try {
            bbdd.update("update Departamentos set dnombre = ?, loc = ? where dept_no =?", this.dnombre, this.loc, this.dept_no );
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(ResultSet rs){
        try {
            rs.beforeFirst();
            //Updating the salary of each employee by 5000
            while(rs.next()) {
                //Retrieve by column name
                rs.updateString("dnombre", this.dnombre);
                rs.updateString("loc", this.loc);
                rs.updateRow();
            } } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Elimina un departamento
     * 
     * @param bbdd Clase para las operaciones con la bbdd
     * @param dept_no Número del departamento que queremos eliminar
     */
    public static void delete(OperacionesBBDD bbdd, int dept_no){
        try {
            bbdd.delete("delete from Departamentos where dept_no =(?)", dept_no);
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**************************************************************************
     * EJECUCIÓN DE PROCEDIMIENTOS Y FUNCIONES
     **************************************************************************/
    /**
     * Llamada al procedimiento p_nombre_depart, almacenado en la bbdd 
     * 
     * @param bbdd Para realizar la conexión a la bbdd
     * @param dept_no Número del departamento que le pasaremos al procedimiento
     * @return  Nombre del departamento
     */
    public static String pNombreDepart(OperacionesBBDD bbdd, int dept_no) {
        CallableStatement llamada;
        String dnombre = null;
        try {     
            //Vamos a llamar a un procedimiento con la siguiente cabecera
            //PROCEDURE P_NOMBRE_DEPART(NDEPART NUMBER, NOMBRE_DEPART OUT VARCHAR2)
            //Preparamos el string para la llamada:
            String sql = "{call p_nombre_depart (?,?)}"; 
            
            //Creamos un objeto llamando al método prepareCall:
            llamada=bbdd.getConexion().prepareCall(sql);
            
            //Indicamos cuáles son los parámetros de entrada y cuales los de salida
            //Le damos valor al parámetro de entrada:
            llamada.setInt(1, dept_no);
            //Registramos el parámetro de salida de la función:
            llamada.registerOutParameter(2, Types.VARCHAR); // Indicarle siempre el tipo que es en la BASE DE DATOS
            
            //Realizamos la llamada al procedimiento:
            llamada.executeUpdate();
            
            //Obtenemos el valor del primer parámetro de salida
            dnombre = llamada.getString(2);
                     
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  dnombre;
    }
    
    /**
     * Llamada a la función f_nombre_depart, almacenada en la bbdd 
     * 
     * @param bbdd Para realizar la conexión a la bbdd
     * @param dept_no Número del departamento que le pasaremos a la función
     * @return  Nombre del departamento
     */
    public static String fNombreDepart(OperacionesBBDD bbdd, int dept_no) {
        CallableStatement llamada;
        String dnombre = null;
        try {     
            String sql = "{?=call f_nombre_depart (?)}";
            llamada=bbdd.getConexion().prepareCall(sql);
            // Primer parametro de entrada
            llamada.setInt(2, dept_no);
            // Parametro de salida
            llamada.registerOutParameter(1, Types.VARCHAR);
            llamada.executeUpdate();
            dnombre = llamada.getString(1);
                     
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  dnombre;
    }
    
}
