package org.example;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.example.model.Empresa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "newIndexHandler")
@ViewScoped
public class NewIndexHandler implements Serializable {
    private static final long serialVersionUID = 1L;
    private String razonSocial;
    private String nombrePublico;
    private String direccion;
    private String buscar = "";
    private long nroEmpresa;
    private List<Empresa> empresas = new ArrayList<>();

    @EJB
    EmprLocal empresaBean;

    public NewIndexHandler(){}

    public void agregarEmpresa(){
        empresas.add(empresaBean.agregar(razonSocial, nombrePublico, direccion));
    }

    public void listarEmpresas(){
        empresas = (buscar.isEmpty()) ? empresaBean.getEmpresas("", "") : empresaBean.getEmpresas("Todos los campos", buscar);
    }

    public void borrarEmpresa(long nroEmpresa){
        empresaBean.eliminarEmpresa(nroEmpresa);
        listarEmpresas();
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

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public long getNroEmpresa() {
        return nroEmpresa;
    }

    public void setNroEmpresa(long nroEmpresa) {
        this.nroEmpresa = nroEmpresa;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }
}
