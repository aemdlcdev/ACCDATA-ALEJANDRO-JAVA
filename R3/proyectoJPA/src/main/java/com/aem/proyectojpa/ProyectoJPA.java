/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.aem.proyectojpa;

import jakarta.jms.Connection;
import jakarta.transaction.Transaction;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 */
public class ProyectoJPA {
  
    static EntityManagerFactory emfactory;
    static EntityManager entityManager;
    
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) {
        inicializaFactory();
        
        // eliminarDeptNo();
        
        // modSalarioYDept();
        
        // eliminarEmpleado();
        
        // mostrarDeptYEmpleados();
        
        // insertarEmpleado();
        
    }
    

    public static void mostrarDeptYEmpleados() {
        System.out.println("Introduce el numero del departamento:");
        short deptNo = getShort();

        Query queryDept = entityManager.createQuery("SELECT d FROM Departamentos d WHERE d.deptNo = :deptNo");
        queryDept.setParameter("deptNo", deptNo);

        Departamentos departamento = (Departamentos) queryDept.getSingleResult();

        if (departamento == null) {
            System.out.println("No existe un departamento con el numero " + deptNo);
            return;
        }

        System.out.println("Departamento: " + departamento.getDnombre());
        System.out.println("Ubicación: " + departamento.getLoc());

        System.out.println("Empleados del departamento " + departamento.getDnombre() + ":");
        for (Empleados empleado : departamento.getEmpleadosCollection()) {
            System.out.println("Empleado ID: " + empleado.getEmpNo());
            System.out.println("Apellido: " + empleado.getApellido());
            System.out.println("Oficio: " + empleado.getOficio());
            System.out.println("Salario: " + empleado.getSalario());
            System.out.println("Fecha de alta: " + empleado.getFechaAlt());
            System.out.println("----------------------------");
        }

        entityManager.close();
        emfactory.close();
    }

    public static void eliminarDeptNo() {
        System.out.println("Indique el numero de departamento:");
        int deptDel = getShort();

        Query queryCheckDept = entityManager.createQuery("SELECT COUNT(d) FROM Departamentos d WHERE d.deptNo = :deptNo");
        queryCheckDept.setParameter("deptNo", deptDel);

        long count = (long) queryCheckDept.getSingleResult();
        if (count == 0) {
            System.out.println("Error: No existe un departamento con el numero " + deptDel);
            return;
        }

        try {
            Query queryDelDept = entityManager.createQuery("DELETE FROM Departamentos d WHERE d.deptNo = :deptNo");
            queryDelDept.setParameter("deptNo", deptDel);

            entityManager.getTransaction().begin();
            int deletedCount = queryDelDept.executeUpdate();
            entityManager.getTransaction().commit();

            System.out.println(deletedCount + " departamento(s) eliminado(s)");
        } catch (Exception e) {
            System.out.println("No se puede eliminar el departamento con el numero " + deptDel + " porque tiene empleados asignados");
            entityManager.getTransaction().rollback();  // Hago el rollback porq hay error
        } finally {
            entityManager.close();
            emfactory.close();
        }
    }

    public static void modSalarioYDept() {
        
        System.out.println("Elige el numero de empleado para modificar:");
        short numEmpleado = getShort();

        System.out.println("Introduce el nuevo salario:");
        double salario = getDouble();

        System.out.println("Introduce el nuevo numero de departamento:");
        short newDept = getShort();

        Departamentos departamento = entityManager.find(Departamentos.class, BigDecimal.valueOf(newDept));
        if (departamento == null) {
            System.out.println("Error: No existe un departamento con el numero " + newDept);
            return;
        }

        Query query = entityManager.createQuery(
            "UPDATE Empleados e SET e.salario = :salarioNuevo, e.deptNo = :deptNuevo WHERE e.empNo = :empNoIn"
        );

        query.setParameter("salarioNuevo", BigDecimal.valueOf(salario)); 
        query.setParameter("deptNuevo", departamento); 
        query.setParameter("empNoIn", numEmpleado);

        entityManager.getTransaction().begin();
        int countModified = query.executeUpdate();
        System.out.println("Modificado(s) " + countModified + " empleado(s)");
        entityManager.getTransaction().commit();

        entityManager.close();
        emfactory.close();
    }
    
    public static void eliminarEmpleado() {
        System.out.println("Introduce el numero del empleado que desea eliminar:");
        short delEmple = getShort();

        Query queryCheckEmp = entityManager.createQuery("SELECT COUNT(e) FROM Empleados e WHERE e.empNo = :empNo");
        queryCheckEmp.setParameter("empNo", delEmple);

        long count = (long) queryCheckEmp.getSingleResult();
        if (count == 0) {
            System.out.println("Error: No existe un empleado con el numero " + delEmple);
            return;
        }

        Query query = entityManager.createQuery("DELETE FROM Empleados e WHERE e.empNo = :empNo");
        query.setParameter("empNo", delEmple);

        entityManager.getTransaction().begin();
        int deletedCount = query.executeUpdate();
        entityManager.getTransaction().commit();

        System.out.println(deletedCount + " empleado(s) eliminado(s).");

        entityManager.close();
        emfactory.close();
    }
    
    public static void insertarEmpleado() {
        
        System.out.println("Introduce el numero del empleado:");
        short empNo = getShort();  

        Query queryCheckEmp = entityManager.createQuery("SELECT COUNT(e) FROM Empleados e WHERE e.empNo = :empNo");
        queryCheckEmp.setParameter("empNo", empNo);
        long count = (long) queryCheckEmp.getSingleResult();
        if (count > 0) {
            System.out.println("Error: Ya existe un empleado con el numero " + empNo);
            return;
        }

        System.out.println("Introduce el apellido del empleado:");
        String apellido = getString();  

        System.out.println("Introduce el oficio del empleado:");
        String oficio = getString();

        System.out.println("Introduce el numero de director del empleado (puede dejarse vacío si no aplica):");
        short dir = getShort();  

        System.out.println("Introduce la fecha de alta (formato: yyyy-mm-dd):");
        String fechaStr = getString();  
        Date fechaAlta = Date.valueOf(fechaStr);  

        System.out.println("Introduce el salario del empleado:");
        double salario = getDouble();  

        System.out.println("Introduce la comision del empleado:");
        double comision = getDouble();  

        System.out.println("Introduce el numero del departamento:");
        short deptNo = getShort(); 

        Query queryDept = entityManager.createQuery("SELECT d FROM Departamentos d WHERE d.deptNo = :deptNo");
        queryDept.setParameter("deptNo", deptNo);
        Departamentos departamento = (Departamentos) queryDept.getSingleResult();

        if (departamento == null) {
            System.out.println("Error: No existe un departamento con el numero " + deptNo);
            return;
        }

        Empleados nuevoEmpleado = new Empleados();
        nuevoEmpleado.setEmpNo(empNo);
        nuevoEmpleado.setApellido(apellido);
        nuevoEmpleado.setOficio(oficio);
        nuevoEmpleado.setDir(dir);
        nuevoEmpleado.setFechaAlt(fechaAlta);
        nuevoEmpleado.setSalario(BigDecimal.valueOf(salario));
        nuevoEmpleado.setComision(BigDecimal.valueOf(comision));
        nuevoEmpleado.setDeptNo(departamento);

        entityManager.getTransaction().begin();
        entityManager.persist(nuevoEmpleado);
        entityManager.getTransaction().commit();

        System.out.println("Empleado con numero " + empNo + " insertado correctamente");
    }

    // Metodos de utilidad
    
    public static void inicializaFactory(){
    
        emfactory = Persistence.createEntityManagerFactory("com.aem_proyectoJPA_jar_1.0-SNAPSHOTPU");
        entityManager = emfactory.createEntityManager();
        
    }
    
    private static short getShort(){
        
        short num = 0;
        
        try {

            num = Short.parseShort(in.readLine());
   
        } catch (IOException ex) {
            Logger.getLogger(ProyectoJPA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return num;
    }
        
    private static double getDouble(){
        
        double num = 0.0;
        
        try {

            num = Double.parseDouble(in.readLine());
   
        } catch (IOException ex) {
            Logger.getLogger(ProyectoJPA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return num;
    }
    
    private static String getString(){
        String str ="";
        
        try {

            str = in.readLine();
   
        } catch (IOException ex) {
            Logger.getLogger(ProyectoJPA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return str;
    }
    
}
