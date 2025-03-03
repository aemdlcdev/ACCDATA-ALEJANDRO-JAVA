/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.accesosqlite.modelo;

import com.aem.accesosqlite.AccesoBBDD;
import com.aem.accesosqlite.bbdd.OperacionesBBDD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
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
    private int director;
    private String fechaAlta;
    private double salario;
    private double comision;
    private int deptId;
    
    private OperacionesBBDD operaciones = OperacionesBBDD.getInstance();
    
    public Empleado(){
        
    }

    public Empleado(int id, String apellido, String oficio, int director, String fecha, double salario, double comision, int deptId) {
        this.id = id;
        this.apellido = apellido;
        this.oficio = oficio;
        this.director = director;
        this.fechaAlta = fecha;
        this.salario = salario;
        this.comision = comision;
        this.deptId = deptId;
    }

    
    
    public void insertarDatosEmpleado(int id, String apellido, String oficio, int director, double salario, double comision, int deptId) {
        
        /*
            SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyy");
            java.util.Date fechaUtil = null;
            
            String fecha = "08/11/2024";
            fechaUtil = s.parse(fecha);
            java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
        */

        try {
            java.sql.Date fechaSql = java.sql.Date.valueOf(java.time.LocalDate.now()); // Conversión directa con la fecha del sistema

            if (apellido == null || oficio == null) {
                System.out.println("El apellido y el oficio no pueden ser nulos.");
                return;
            }
            if (salario < 0) {
                System.out.println("El salario debe ser mayor o igual a 0.");
                return;
            }

            boolean existeEmpleado = comprobarIdEmpleado(id);
            if (existeEmpleado) {
                System.out.println("Ya existe un empleado con esa ID.");
                return;
            }

            try {
                operaciones.insert("insert into Empleados values (?,?,?,?,?,?,?,?)", id, apellido, oficio, director,
                        fechaSql, salario, comision, deptId);
                System.out.println("Empleado añadido correctamente.");
            } catch (SQLException ex) {
                Logger.getLogger(AccesoBBDD.class.getName()).log(Level.SEVERE, null, ex);
                switch (ex.getErrorCode()) {
                    case 2291 -> System.out.println("El departamento no existe. || Error: " + ex.getErrorCode());
                    default -> System.out.println("Error desconocido");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    private boolean comprobarIdEmpleado(int id){
        String sql = "SELECT * FROM empleados WHERE emp_no = ?";
        Optional<ResultSet> rs = null;
        boolean existe = false;
        try {
             rs = operaciones.select(sql,id);
             
             if(rs.get().next()){
                 existe = true;
             } 
             
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }
    
    
    
    public void selectById(OperacionesBBDD bbdd, int id){
        
        try {
            Optional<ResultSet> rs = bbdd.select("SELECT * FROM empleados WHERE emp_no = ?", id);
            
            if(rs.isPresent()){
                while(rs.get().next()){
                    this.id = rs.get().getInt("emp_no");
                    this.apellido = rs.get().getString("apellido");
                    this.oficio = rs.get().getString("oficio");
                    this.director = rs.get().getInt("dir");
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
    
    
    public static Optional<ResultSet> selectGenerico(OperacionesBBDD bbdd, String query){
        Optional<ResultSet> rs = null;
        try {
            
            rs = bbdd.select(query);
            
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
                            rs.get().getString("apellido") + " Oficio: " + rs.get().getString("oficio") + " Director: " + 
                            rs.get().getInt("dir") + " Fecha alta: " + rs.get().getString("fecha_alt") + " Salario: " + 
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
                    this.director,
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
    
    public static void updateSalario(OperacionesBBDD bbdd) {
        int aumentoSalario = 100;
//        String sqlSelect = "SELECT * FROM EMPLEADOS WHERE DEPT_NO = 10";
          String sql = "UPDATE EMPLEADOS SET salario = salario + ? WHERE dept_no = 15";
        
        try {
            
//            Optional<ResultSet> rs = bbdd.select(sqlSelect);
            int numFilas = bbdd.update(sql, aumentoSalario);
            
            if (numFilas != 0){ // rs.get().next()
                
                System.out.println("Salario actualizado");
            } else{
                System.out.println("Departamento no encontrado");
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void updateSalarioPorcentaje(OperacionesBBDD bbdd , int deptNo, double porcentaje){
        String sql = "UPDATE empleados SET salario = salario * ? WHERE dept_no = ?";
        double porcentajeCalculado = 1 + (porcentaje/100);
        try {
            int registrosCambiados = bbdd.update(sql, porcentajeCalculado, deptNo);
            System.out.println("Alterados " + registrosCambiados + " registros");
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
    
    public static void selectMaxSalario(OperacionesBBDD bbdd){
        
        try {
            String sql = "select e.apellido, e.salario, d.dnombre from empleados e join departamentos d on e.dept_no = d.dept_no"
                    + " where e.salario = (select max(salario) from empleados)";
            
            Optional<ResultSet> rso = bbdd.select(sql);
            
            if(rso.isPresent()){
                ResultSet rs = rso.get();
                while(rs.next()){
                
                    String apellido = rs.getString("Apellido");
                    String salario = rs.getString("SALARIO");
                    String dnombre = rs.getString("DNOMBRE");
                    
                    System.out.println("Apellido: " + apellido + "| Salario: " + salario + "| Dnombre: " + dnombre);
                }
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
