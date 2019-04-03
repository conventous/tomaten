import messaging.MessageReceiverGateway;

import javax.jms.TextMessage;

public class SensorGateway {
    private MessageReceiverGateway messageReceiverGateway;

    public SensorGateway() {
        this.messageReceiverGateway = new MessageReceiverGateway("");
        this.messageReceiverGateway.setListener(message -> sensordataReceived((TextMessage) message));
    }

    public void sensordataReceived(TextMessage message){

    }
}
