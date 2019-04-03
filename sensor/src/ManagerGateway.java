import messaging.ApplicationGateway;
import messaging.MessageReceiverGateway;
import messaging.MessageSenderGateway;

public class ManagerGateway extends ApplicationGateway {

    public ManagerGateway() {
        super(new MessageSenderGateway("SensorDataTEMP"));
    }
}
