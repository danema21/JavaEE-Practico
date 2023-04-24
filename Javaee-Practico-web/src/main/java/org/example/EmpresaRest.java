package org.example;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.jms.*;
import jakarta.jws.WebMethod;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@JMSDestinationDefinitions(
        value = {
                @JMSDestinationDefinition(
                        name = "java:/queue/altaEmpresaRest",
                        interfaceName = "jakarta.jms.Queue",
                        destinationName = "altaEmpresaQueueRest"
                )
        }
)
@RequestScoped
@Path("/empresa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class EmpresaRest {
    @Inject
    private JMSContext context;
    @Resource(lookup = "java:/queue/altaEmpresa")
    private Queue queue;

    @POST
    @Path("/agregar")
    public String agregarEmpresa(@QueryParam("mensaje") String mensaje){
        final Destination destination = queue;
        JMSProducer producer = context.createProducer();
        producer.send(destination, mensaje);
        return "entro al metodo";
    }
}
