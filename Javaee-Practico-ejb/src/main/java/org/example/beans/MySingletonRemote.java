package org.example.beans;

import jakarta.ejb.Remote;
import org.example.model.Empresa;

import java.util.List;

@Remote
public interface MySingletonRemote {
    void agregar(String razonSocial, String nombrePublico, String direccion);
    List<Empresa> getEmpresas(String filtro, String valor);
    void eliminarEmpresa(long nroEmpresa);
}
