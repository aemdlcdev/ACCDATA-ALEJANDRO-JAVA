/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.accesosqlite.modelo;

import com.aem.accesosqlite.AccesoBBDD;
import com.aem.accesosqlite.bbdd.OperacionesBBDD;
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
public class Departamento {
    private int dept_no;
    private String nombre;
    private String localidad;
    private OperacionesBBDD operaciones = OperacionesBBDD.getInstance();

    public Departamento(int id, String nombre, String localidad) {
        this.dept_no = id;
        this.nombre = nombre;
        this.localidad = localidad;
    }

    public Departamento() {
    }
    
    
    public void insertarDatosDepartamento(){
       
        try {
            operaciones.insert("insert into Departamentos values (?,?,?)", this.dept_no, this.nombre, this.localidad);
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void selectById (OperacionesBBDD bbdd, int id){
        try {
            Optional<ResultSet> rs = bbdd.select("SELECT * FROM departamentos WHERE dept_no = ?", id);
            
            if(rs.isPresent()){
                try {
                    while (rs.get().next()){
                        this.dept_no = (rs.get().getInt("dept_no"));
                        this.nombre = (rs.get().getString("dnombre"));
                        this.localidad = (rs.get().getString("loc"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Optional<ResultSet> selectAll(OperacionesBBDD bbdd){
        Optional<ResultSet> rs = null;
        try {
            
            rs = bbdd.select("SELECT * FROM departamentos");
            
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public static void mostrarResultSet(Optional<ResultSet> rs){
        try {
            if(rs.isPresent()){
            
                while(rs.get().next()){
                    System.out.println("Número departamento: " + rs.get().getInt("dept_no") + " Nombre: " +
                            rs.get().getString("dnombre") + " Localidad: " + rs.get().getString("loc"));
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(OperacionesBBDD bbdd){
        try {
            bbdd.update("UPDATE departamentos SET dnombre = ?, loc = ? WHERE dept_no = ?", 
                    this.nombre, 
                    this.localidad, 
                    this.dept_no);
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void delete(OperacionesBBDD bbdd, int dept_no){
        try {
            bbdd.delete("DELETE FROM departamentos WHERE dept_no = ?", dept_no);
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String toString() {
        return "Departamento{" + "dept_no=" + dept_no + ", nombre=" + nombre + ", localidad=" + localidad + '}';
    }
    
    
    
}
