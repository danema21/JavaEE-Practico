package org.example.beans;

import jakarta.ejb.Local;
import org.example.model.Empresa;

import java.util.List;

@Local
public interface MySingletonLocal {
    void agregar(String razonSocial, String nombrePublico, String direccion);
    List<Empresa> getEmpresas(String filtro, String valor);
    void eliminarEmpresa(long nroEmpresa);
}
