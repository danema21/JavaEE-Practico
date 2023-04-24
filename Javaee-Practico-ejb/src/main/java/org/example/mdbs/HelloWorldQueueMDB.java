package org.example.mdbs;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

import java.util.logging.Logger;

@MessageDriven(name = "HelloWorldQueueMDB", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "queue/HELLOWORLDMDBQueue"),
        @ActivationConfigProperty(propertyValue = "destinationType", propertyName = "jakarta.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class HelloWorldQueueMDB implements MessageListener {
    private static final Logger LOGGER = Logger.getLogger(HelloWorldQueueMDB.class.toString());

    @Override
    public void onMessage(Message message) {
        TextMessage msg = null;
        try {
            if (message instanceof TextMessage){
                msg = (TextMessage) message;
                LOGGER.info("Received Message from queue: " + msg.getText());
            }else {
                LOGGER.warning("Message of wrong type " + message.getClass().getName());
            }
        }catch (JMSException e){
            throw new RuntimeException(e);
        }
    }
}
