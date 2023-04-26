package org.example.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Empresa implements Serializable {
    public static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    long nroEmpresa;
    String razonSocial;
    String nombrePublico;
    String direccion;
    LocalDateTime fechaCreacion;

    public Empresa() {
    }

    public Empresa(String razonSocial, String nombrePublico, String direccion) {
        this.razonSocial = razonSocial;
        this.nombrePublico = nombrePublico;
        this.direccion = direccion;
    }

    public long getNroEmpresa() {
        return nroEmpresa;
    }

    public void setNroEmpresa(long nroEmpresa) {
        this.nroEmpresa = nroEmpresa;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombrePublico() {
        return nombrePublico;
    }

    public void setNombrePublico(String nombrePublico) {
        this.nombrePublico = nombrePublico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
