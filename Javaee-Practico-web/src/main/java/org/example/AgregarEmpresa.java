package org.example;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AgregarEmpresa", value = "/agregar-empresa")
public class AgregarEmpresa extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    EmprLocal empresa;
    public AgregarEmpresa() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String razonsocial = request.getParameter("razonsocial");
        String nombrepublico = request.getParameter("nombrepublico");
        String direccion = request.getParameter("direccion");

        try {
            empresa.agregar(razonsocial, nombrepublico, direccion);
            response.getWriter().println("<h4>se ha creado la empresa</h4>");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
