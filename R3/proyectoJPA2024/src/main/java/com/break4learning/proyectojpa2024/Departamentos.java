/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.break4learning.proyectojpa2024;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Break4Learning by Javier García-Retamero Redondo
 * @version 1.0
 * Created on 8 dic 2024
 */
@Entity
@Table(name = "DEPARTAMENTOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamentos.findAll", query = "SELECT d FROM Departamentos d"),
    @NamedQuery(name = "Departamentos.findByDeptNo", query = "SELECT d FROM Departamentos d WHERE d.deptNo = :deptNo"),
    @NamedQuery(name = "Departamentos.findByDnombre", query = "SELECT d FROM Departamentos d WHERE d.dnombre = :dnombre"),
    @NamedQuery(name = "Departamentos.findByLoc", query = "SELECT d FROM Departamentos d WHERE d.loc = :loc")})
public class Departamentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Basic(optional = false)
    @NotNull
    @Column(name = "DEPT_NO", length=2)
    private Short deptNo;
    
    @Column(name = "DNOMBRE", length=15,  nullable= false)
    private String dnombre;
    
    @Column(name = "LOC", length=15)
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

    @XmlTransient
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
        return "com.break4learning.proyectojpa2024.Departamentos[ deptNo=" + deptNo + " ]";
    }

}
