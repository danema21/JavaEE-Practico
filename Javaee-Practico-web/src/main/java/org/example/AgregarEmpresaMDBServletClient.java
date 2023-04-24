package org.example;

import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.jms.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@JMSDestinationDefinitions(
        value = {
                @JMSDestinationDefinition(
                        name = "java:/queue/altaEmpresa",
                        interfaceName = "jakarta.jms.Queue",
                        destinationName = "altaEmpresaQueue"
                )
        }
)
@WebServlet(name = "AgregarEmpresaMDBServletClient", value = "/agregar-empresa-mdb")
public class AgregarEmpresaMDBServletClient extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    private JMSContext context;
    @Resource(lookup = "java:/queue/altaEmpresa")
    private Queue queue;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Destination destination = queue;
        String mensaje = request.getParameter("mensaje");
        JMSProducer producer = context.createProducer();
        producer.send(destination, mensaje);
        response.getWriter().println("<h4>se ha mandado el mensaje a la cola</h4>");
    }
}
