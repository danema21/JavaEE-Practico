package org.example;

import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.jms.*;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@JMSDestinationDefinitions(
        value = {
                @JMSDestinationDefinition(
                        name = "java:/queue/altaEmpresaSoap",
                        interfaceName = "jakarta.jms.Queue",
                        destinationName = "altaEmpresaQueueSoap"
                )
        }
)
@WebService
public class EmpresaSoap {
    @Inject
    private JMSContext context;
    @Resource(lookup = "java:/queue/altaEmpresa")
    private Queue queue;

    @WebMethod
    public void agregarEmpresa(String mensaje){
        final Destination destination = queue;
        JMSProducer producer = context.createProducer();
        producer.send(destination, mensaje);
    }
}
