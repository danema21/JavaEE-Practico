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
                        name = "java:/queue/HELLOWORLDMDBQueue",
                        interfaceName = "jakarta.jms.Queue",
                        destinationName = "HelloWorldMDBQueue"
                )
        }
)
@WebServlet(name = "HelloWorldMDBServletClient", value = "/helloworl-mdb")
public class HelloWorldMDBServletClient extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int MSG_COUNT = 5;
    @Inject
    private JMSContext context;
    @Resource(lookup = "java:/queue/HELLOWORLDMDBQueue")
    private Queue queue;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("txt/html");
        PrintWriter out = response.getWriter();
        out.write("<h1>Quickstart: Example use of JMS message drive bean</h1>");

        try {
            final Destination destination = queue;
            out.write("<p>Sending messages to " + destination + "</p>");
            out.write("<h2>The following messages will be sent to the destination:</h2>");

            for (int i=0; i < MSG_COUNT; i++){
                String text = "This is message " + (i+1);
                JMSProducer producer = context.createProducer();
                producer.send(destination, text);
                out.write("Message (" + i + "): " + text + "</br>");
            }
        }finally {
            if(out != null){
                out.close();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
