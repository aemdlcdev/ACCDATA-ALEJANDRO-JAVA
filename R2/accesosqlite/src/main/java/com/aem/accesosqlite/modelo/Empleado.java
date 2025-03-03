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
public class Empleado {
    private int id;
    private String apellido;
    private String oficio;
    private String direccion;
    private String fechaAlta;
    private double salario;
    private double comision;
    private int deptId;
    
    private OperacionesBBDD operaciones = OperacionesBBDD.getInstance();

    public Empleado(int id, String apellido, String oficio, String direccion, String fecha, double salario, double comision, int deptId) {
        this.id = id;
        this.apellido = apellido;
        this.oficio = oficio;
        this.direccion = direccion;
        this.fechaAlta = fecha;
        this.salario = salario;
        this.comision = comision;
        this.deptId = deptId;
    }

    
    
    public void insertarDatosEmpleado(){
       
        try {
            operaciones.insert("insert into Empleados values (?,?,?,?,?,?,?,?)", this.id,this.apellido,this.oficio,this.direccion,
                    this.fechaAlta,this.salario,this.comision,this.deptId);
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void selectById(OperacionesBBDD bbdd, int id){
        
        try {
            Optional<ResultSet> rs = bbdd.select("SELECT * FROM empleados WHERE emp_no = ?", id);
            
            if(rs.isPresent()){
                while(rs.get().next()){
                    this.id = rs.get().getInt("emp_no");
                    this.apellido = rs.get().getString("apellido");
                    this.oficio = rs.get().getString("oficio");
                    this.direccion = rs.get().getString("dir");
                    this.fechaAlta = rs.get().getString("fecha_alt");
                    this.salario = rs.get().getDouble("salario");
                    this.comision = rs.get().getDouble("comision");
                    this.deptId = rs.get().getInt("dept_no");
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static Optional<ResultSet> selectAll(OperacionesBBDD bbdd){
        Optional<ResultSet> rs = null;
        try {
            
            rs = bbdd.select("SELECT * FROM empleados");
            
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
    
    public static void mostrarResultSet(Optional<ResultSet> rs){
        try{
            if(rs.isPresent()){
                while (rs.get().next()){
                    System.out.println("Numero de empleado: " + rs.get().getInt("emp_no") + " Apellido: " + 
                            rs.get().getString("apellido") + " Oficio: " + rs.get().getString("oficio") + " Direccion: " + 
                            rs.get().getString("dir") + " Fecha alta: " + rs.get().getString("fecha_alt") + " Salario: " + 
                            rs.get().getDouble("salario") + " Comision: " + rs.get().getDouble("comision") + " Numero de departamento: " +
                            rs.get().getInt("dept_no")
                            );
                }
            }
        } catch(SQLException ex){
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public void update(OperacionesBBDD bbdd){
        
        try {
            bbdd.update("UPDATE empleados SET apellido = ?, oficio = ?, dir = ?, fecha_alt = ?, salario = ?, "
                    + "comision = ?, dept_no = ? WHERE emp_no = ?",
                    
                    this.apellido,
                    this.oficio,
                    this.direccion,
                    this.fechaAlta,
                    this.salario,
                    this.comision,
                    this.deptId,
                    this.id
            );
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void delete(OperacionesBBDD bbdd, int emp_no){
        try {
            bbdd.delete("DELETE FROM empleados WHERE emp_no = ?", emp_no);
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
