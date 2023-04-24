package org.example.mdbs;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import org.example.EmprLocal;
import org.example.EmprRemote;

import java.util.logging.Logger;

@MessageDriven(name = "AgregarEmpresaQueueMDB", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "queue/altaEmpresa"),
        @ActivationConfigProperty(propertyValue = "destinationType", propertyName = "jakarta.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class AgregarEmpresaQueueMDB implements MessageListener {
    private static final Logger LOGGER = Logger.getLogger(AgregarEmpresaQueueMDB.class.toString());

    @EJB
    EmprRemote empresaBean;

    @Override
    public void onMessage(Message message) {
        TextMessage msg;
        try {
            if(message instanceof TextMessage){
                msg = (TextMessage) message;
                LOGGER.info("Mensaje de empresa a agregar: " + msg.getText());
                String[] splitedMsg = msg.getText().split("\\|");
                if(splitedMsg.length == 3){
                    empresaBean.agregar(splitedMsg[0], splitedMsg[1], splitedMsg[2]);
                    LOGGER.info("Empresa creada con exito");
                }else {
                    LOGGER.warning("El mensaje no cumple con los requisitos para crear una empresa");
                }
            }else {
                LOGGER.warning("Mensaje de tipo incorrecto " + message.getClass().getName());
            }
        }catch (JMSException e){
            throw new RuntimeException(e);
        }
    }
}
