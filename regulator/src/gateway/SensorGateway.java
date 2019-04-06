package gateway;

import com.google.gson.Gson;
import messaging.MessageReceiverGatewayActiveMQ;
import messaging.models.SensorData;
import messaging.models.SensorDataTemp;
import messaging.models.SensorDataWater;
import regulator.RegulationManager;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public class SensorGateway {
    private final RegulationManager regulationManager;
    private final Gson gson;

    private MessageReceiverGatewayActiveMQ messageReceiverGatewayTemp;
    private MessageReceiverGatewayActiveMQ messageReceiverGatewayWater;

    public SensorGateway(RegulationManager regulationManager) {
        this.regulationManager = regulationManager;

        //todo maybe on receivergateway with multiple listeners?
        this.messageReceiverGatewayTemp = new MessageReceiverGatewayActiveMQ("SensorQueueTemp");
        this.messageReceiverGatewayTemp.initConsumerQueue("SensorQueueTemp");
        this.messageReceiverGatewayTemp.setListener(this::sensorDataReceived);

        this.messageReceiverGatewayWater = new MessageReceiverGatewayActiveMQ("SensorQueueWater");
        this.messageReceiverGatewayWater.initConsumerQueue("SensorQueueWater");
        this.messageReceiverGatewayWater.setListener(this::sensorDataReceived);

        this.gson = new Gson();
    }

    //todo synchronized needed?
    private synchronized void sensorDataReceived(Message message){
        TextMessage textMessage = (TextMessage)message;
        SensorData sensorData = null;

        try {
            String sensorDataJSON = textMessage.getText();

            //Op basis van queue deserializen
            if(message.getJMSDestination().toString().equals("queue://SensorQueueTemp")){
                sensorData = gson.fromJson(sensorDataJSON, SensorDataTemp.class);
            }
            else if(message.getJMSDestination().toString().equals("queue://SensorQueueWater")){
                sensorData = gson.fromJson(sensorDataJSON, SensorDataWater.class);
            }
            else{
                //todo invalid queue, just the json string
                System.out.println("gateway.SensorGateway: unknown sensor type queue...");
            }

            regulationManager.absorbdata(sensorData);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
