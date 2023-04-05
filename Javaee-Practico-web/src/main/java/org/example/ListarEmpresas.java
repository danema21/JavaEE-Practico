package org.example;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Empresa;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListarEmpresas", value = "/listar-empresas")
public class ListarEmpresas extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    EmprLocal empresa;
    public ListarEmpresas() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filtro = request.getParameter("seleccionar");
        String valor = request.getParameter("filtro");
        List<Empresa> empresas = empresa.getEmpresas(filtro, valor);

        empresas.forEach(item -> {
            try {
                response.getWriter().println(
                        "<form action=\"eliminar-empresa\" method=\"post\">\n" +
                        "    <h2>"+ item.getNroEmpresa() + ": " + item.getRazonSocial() +"</h2>\n" +
                        "    <h3>"+ item.getNombrePublico() + "</h3>\n" +
                        "    <h4>"+ item.getDireccion() + "/ " + item.getFechaCreacion() + "</h4>\n" +
                        "    <input type=\"hidden\" name=\"delete\" value=\"" + item.getNroEmpresa() +"\"></input>" +
                        "    <button type=\"submit\">delete</button>" +
                        "</form><br/>");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
