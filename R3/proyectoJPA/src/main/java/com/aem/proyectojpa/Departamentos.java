/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.proyectojpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 2 dic 2024
 *
 */
@Entity
@Table(name = "DEPARTAMENTOS")
@NamedQueries({
    @NamedQuery(name = "Departamentos.findAll", query = "SELECT d FROM Departamentos d"),
    @NamedQuery(name = "Departamentos.findByDeptNo", query = "SELECT d FROM Departamentos d WHERE d.deptNo = :deptNo"),
    @NamedQuery(name = "Departamentos.findByDnombre", query = "SELECT d FROM Departamentos d WHERE d.dnombre = :dnombre"),
    @NamedQuery(name = "Departamentos.findByLoc", query = "SELECT d FROM Departamentos d WHERE d.loc = :loc")})
public class Departamentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "DEPT_NO")
    private Short deptNo;
    @Column(name = "DNOMBRE")
    private String dnombre;
    @Column(name = "LOC")
    private String loc;
    @OneToMany(mappedBy = "deptNo")
    private Collection<Empleados> empleadosCollection;

    public Departamentos() {
    }

    public Departamentos(Short deptNo) {
        this.deptNo = deptNo;
    }

    public Short getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Short deptNo) {
        this.deptNo = deptNo;
    }

    public String getDnombre() {
        return dnombre;
    }

    public void setDnombre(String dnombre) {
        this.dnombre = dnombre;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Collection<Empleados> getEmpleadosCollection() {
        return empleadosCollection;
    }

    public void setEmpleadosCollection(Collection<Empleados> empleadosCollection) {
        this.empleadosCollection = empleadosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deptNo != null ? deptNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamentos)) {
            return false;
        }
        Departamentos other = (Departamentos) object;
        if ((this.deptNo == null && other.deptNo != null) || (this.deptNo != null && !this.deptNo.equals(other.deptNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aem.proyectojpa.Departamentos[ deptNo=" + deptNo + " ]";
    }

}
