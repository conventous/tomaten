import com.google.gson.Gson;
import messaging.MessageSenderGatewayActiveMQ;
import messaging.models.SensorData;

public class ManagerGateway {

    private MessageSenderGatewayActiveMQ messageSenderGatewayActiveMQ;
    private Gson gson;

    public ManagerGateway() {
       this.messageSenderGatewayActiveMQ = new MessageSenderGatewayActiveMQ( "SensorQueue");
       this.gson = new Gson();
    }

    public void sendSensorData(SensorData sensorData){
        String sensorDataJSON = gson.toJson(sensorData);
        this.messageSenderGatewayActiveMQ.sendMessage(sensorDataJSON);
        System.out.println("Send " + sensorDataJSON + " to queue");
    }
}
