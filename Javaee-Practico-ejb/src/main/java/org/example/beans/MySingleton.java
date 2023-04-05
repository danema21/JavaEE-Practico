package org.example.beans;

import jakarta.ejb.Singleton;
import org.example.model.Empresa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Singleton
public class MySingleton implements MySingletonLocal, MySingletonRemote{
    List<Empresa> empresas = new ArrayList<>();
    long nroEmpresa = 0;
    public MySingleton(){}
    @Override
    public void agregar(String razonSocial, String nombrePublico, String direccion){
        Empresa empresa = new Empresa(razonSocial,nombrePublico,direccion);
        empresa.setFechaCreacion(LocalDateTime.now());
        nroEmpresa += 1;
        empresa.setNroEmpresa(nroEmpresa);
        empresas.add(empresa);
    }
    @Override
    public List<Empresa> getEmpresas(String filtro, String valor){
        switch (filtro){
            case "Todos los campos":
                AtomicReference<String> info = new AtomicReference<>("");
                return empresas.stream().filter(empresa -> {
                    info.set("");
                    info.set(info.get().concat(String.valueOf(empresa.getNroEmpresa())));
                    info.set(info.get().concat(empresa.getRazonSocial()));
                    info.set(info.get().concat(empresa.getNombrePublico()));
                    info.set(info.get().concat(empresa.getDireccion()));
                    info.set(info.get().concat(empresa.getFechaCreacion().toString()));
                    return info.get().contains(valor);
                }).collect(Collectors.toList());
            case "Nro":
                return empresas.stream().filter(empresa -> empresa.getNroEmpresa() == Long.parseLong(valor)).collect(Collectors.toList());
            case "Razon Social":
                return empresas.stream().filter(empresa -> empresa.getRazonSocial().contains(valor)).collect(Collectors.toList());
            case "Nombre Publico":
                return empresas.stream().filter(empresa -> empresa.getNombrePublico().contains(valor)).collect(Collectors.toList());
            case "Direccion":
                return empresas.stream().filter(empresa -> empresa.getDireccion().contains(valor)).collect(Collectors.toList());
            case "Fecha":
                return empresas.stream().filter(empresa -> empresa.getFechaCreacion().toString().contains(valor)).collect(Collectors.toList());
            case "Todo":
                return empresas;
            default:
                throw new RuntimeException();
        }
    }

    @Override
    public void eliminarEmpresa(long nroEmpresa) {
        Optional<Empresa> optionalEmpresa = empresas.stream().filter(item -> item.getNroEmpresa() == nroEmpresa).findFirst();
        if(optionalEmpresa.isEmpty())
            throw new RuntimeException();
        empresas.remove(optionalEmpresa.get());
    }
}
