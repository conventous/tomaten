import messaging.MessageSenderGatewayActiveMQ;
import messaging.models.SensorData;

public class ManagerGateway {

    private MessageSenderGatewayActiveMQ messageSenderGatewayActiveMQ;

    public ManagerGateway() {
       this.messageSenderGatewayActiveMQ = new MessageSenderGatewayActiveMQ( "SensorQueue");
    }

    public void sendSensorData(SensorData sensorData){
        this.messageSenderGatewayActiveMQ.sendMessage(sensorData.toString());
    }
}
