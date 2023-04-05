package org.example;

import jakarta.ejb.EJB;
import jakarta.ejb.Local;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import org.example.beans.MySingletonLocal;
import org.example.beans.MySingletonRemote;
import org.example.model.Empresa;

import java.util.List;

@Stateless
@Local(EmprLocal.class)
@Remote(EmprRemote.class)
public class Empr implements EmprLocal, EmprRemote{
    @EJB
    MySingletonLocal singleton;
    public Empr() {
    }
    @Override
    public void agregar(String razonSocial, String nombrePublico, String direccion) {
        singleton.agregar(razonSocial, nombrePublico, direccion);
    }
    @Override
    public List<Empresa> getEmpresas(String filtro, String valor) {
        return singleton.getEmpresas(filtro, valor);
    }
    @Override
    public void eliminarEmpresa(long nroEmpresa) {
        singleton.eliminarEmpresa(nroEmpresa);
    }
}
