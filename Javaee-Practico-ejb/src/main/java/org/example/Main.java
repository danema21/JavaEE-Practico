package org.example;

import org.example.model.Empresa;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NamingException, InterruptedException {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        props.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
        Context ctx = new InitialContext(props);
        String jdniName = "ejb:/Javaee-Practico-ejb//Empr!org.example.EmprRemote";

        EmprRemote empresaBean = (EmprRemote) ctx.lookup(jdniName);

        menu(empresaBean);
    }

    private static void menu(EmprRemote empresaBean) throws InterruptedException {
        System.out.println("******************************");
        System.out.println("EMPRESAS");
        System.out.println("******************************");
        System.out.println("1.Agregar");
        System.out.println("2.Listar");
        System.out.println("3.Eliminar");
        System.out.println("4.Salir");
        System.out.println("------------------------------");
        System.out.println("Elegir opcion: ");
        Scanner in = new Scanner(System.in);
        String opcion = in.nextLine();

        switch (opcion){
            case "1":
                agregarEmpresa(empresaBean);
                break;
            case "2":
                System.out.println("1.Todos los campos");
                System.out.println("2.Nro de empresa");
                System.out.println("3.Razon social");
                System.out.println("4.Nombre publico");
                System.out.println("5.Direccion");
                System.out.println("6.Fecha");
                System.out.println("7.Listar todo");
                System.out.println("------------------------------");
                System.out.println("Elegir filtro:");
                elegirFiltro(in.nextLine(), empresaBean);
                break;
            case "3":
                System.out.println("Introduzca el numero de empresa a eliminar:");
                empresaBean.eliminarEmpresa(Long.parseLong(in.nextLine()));
                break;
            case "4":
                System.exit(0);
                break;
            default:
                System.out.println("input incorrecto");
        }

        menu(empresaBean);
    }

    private static void agregarEmpresa(EmprRemote empresaBean){
        Scanner in = new Scanner(System.in);
        String razonSocial, nombrePublico, direccion;
        System.out.println("Ingrese los datos de la nueva empresa");
        System.out.println("Razon social: ");
        razonSocial = in.nextLine();
        System.out.println("Nombre publico: ");
        nombrePublico = in.nextLine();
        System.out.println("Direccion: ");
        direccion = in.nextLine();

        empresaBean.agregar(razonSocial, nombrePublico, direccion);
    }

    private static void elegirFiltro(String opcion, EmprRemote empresaBean) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese un valor: ");

        switch (opcion){
            case "1":
                empresaBean.getEmpresas("Todos los campos", in.nextLine()).forEach(Main::printEmpresa);
                break;
            case "2":
                empresaBean.getEmpresas("Nro", in.nextLine()).forEach(Main::printEmpresa);
                break;
            case "3":
                empresaBean.getEmpresas("Razon Social", in.nextLine()).forEach(Main::printEmpresa);
                break;
            case "4":
                empresaBean.getEmpresas("Nombre Publico", in.nextLine()).forEach(Main::printEmpresa);
                break;
            case "5":
                empresaBean.getEmpresas("Direccion", in.nextLine()).forEach(Main::printEmpresa);
                break;
            case "6":
                empresaBean.getEmpresas("Fecha", in.nextLine()).forEach(Main::printEmpresa);
                break;
            case "7":
                empresaBean.getEmpresas("Todo", "").forEach(Main::printEmpresa);
                break;
            default:
                System.out.println("input incorrecto");
        }
        System.out.println("ingrese cualquier tecla para continuar");
        in.nextLine();
    }

    private static void printEmpresa(Empresa empresa){
        System.out.println("Nro: " + empresa.getNroEmpresa());
        System.out.println("Razon social: " + empresa.getRazonSocial());
        System.out.println("Nombre publico: " + empresa.getNombrePublico());
        System.out.println("Direccion: " + empresa.getDireccion());
        System.out.println("Fecha: " + empresa.getFechaCreacion());
        System.out.println("------------------------------");
    }
}


















