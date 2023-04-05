package org.example;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "EliminarEmpresa", value = "/eliminar-empresa")
public class EliminarEmpresa extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    EmprLocal empresa;

    public EliminarEmpresa() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            empresa.eliminarEmpresa(Long.parseLong(request.getParameter("delete")));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
