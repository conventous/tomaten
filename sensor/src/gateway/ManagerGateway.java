package gateway;

import com.google.gson.Gson;
import messaging.MessageSenderGatewayActiveMQ;
import messaging.models.SensorData;

public class ManagerGateway {

    private MessageSenderGatewayActiveMQ messageSenderGatewayActiveMQ;
    private Gson gson;

    public ManagerGateway(String type) {
        this.messageSenderGatewayActiveMQ = new MessageSenderGatewayActiveMQ(type);
        this.messageSenderGatewayActiveMQ.initProducerQueue(type);

        this.gson = new Gson();
    }

    public void sendSensorData(SensorData sensorData){
        String sensorDataJSON = gson.toJson(sensorData);
        this.messageSenderGatewayActiveMQ.sendMessage(sensorDataJSON);
        System.out.println("Send " + sensorDataJSON + " to queue");
    }
}
