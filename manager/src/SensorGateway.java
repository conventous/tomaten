import messaging.MessageReceiverGateway;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class SensorGateway {
    private MessageReceiverGateway messageReceiverGateway;

    public SensorGateway() {
        this.messageReceiverGateway = new MessageReceiverGateway("SensorQueue");
        // this.messageReceiverGateway.setListener(message -> sensordataReceived((TextMessage) message));

        this.messageReceiverGateway.setListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {

            }
        });
    }

    public void sensordataReceived(TextMessage message){
        try {
            System.out.println(message.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
