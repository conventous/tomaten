package messaging;

public abstract class ApplicationGateway {
    private MessageSenderGateway messageSenderGateway;
    private MessageReceiverGateway messageReceiverGateway;

    public ApplicationGateway(MessageSenderGateway messageSenderGateway, MessageReceiverGateway messageReceiverGateway) {
        this.messageSenderGateway = messageSenderGateway;
        this.messageReceiverGateway = messageReceiverGateway;
    }

    public ApplicationGateway(MessageSenderGateway messageSenderGateway) {
        this.messageSenderGateway = messageSenderGateway;
    }

    public ApplicationGateway(MessageReceiverGateway messageReceiverGateway) {
        this.messageReceiverGateway = messageReceiverGateway;
    }

    public void sendTextMessage(String messageBody){
        this.messageSenderGateway.sendMessage(messageBody);
    }

    public void messageReceived(String messageBody){
        //todo iets met messagelistener?
    }
}
