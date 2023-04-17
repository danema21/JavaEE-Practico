package org.example;

import jakarta.ejb.Local;
import org.example.model.Empresa;

import java.util.List;

@Local
public interface EmprLocal {
    Empresa agregar(String razonSocial, String nombrePublico, String direccion);
    List<Empresa> getEmpresas(String filtro, String valor);
    void eliminarEmpresa(long nroEmpresa);
}
