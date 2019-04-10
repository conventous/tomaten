package gateway;

import com.google.gson.Gson;
import heater.HeaterManager;
import messaging.MessageReceiverGatewayActiveMQ;
import messaging.MessageSenderGatewayActiveMQ;
import messaging.models.DeviceStatus;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.JMSException;
import javax.jms.Message;

//listens to commands
//listens to heartbeat polls
//reacts to heartbeat polls
public class RegulatorGateway {
    private MessageReceiverGatewayActiveMQ messageReceiverGatewayHeartbeat; //todo will be removed, after dynamic routing
    private MessageReceiverGatewayActiveMQ messageReceiverGatewayCommands;
    private MessageSenderGatewayActiveMQ messageSenderGatewayActiveMQ;

    private final Gson gson;
    private final HeaterManager heaterManager;

    public RegulatorGateway(HeaterManager heaterManager) {
        this.messageReceiverGatewayHeartbeat = new MessageReceiverGatewayActiveMQ("");
        this.messageReceiverGatewayHeartbeat.initConsumerQueue("device#"+heaterManager.getDeviceID());
        this.messageReceiverGatewayHeartbeat.setListener(this::heartbeatPollReceived);

        this.messageSenderGatewayActiveMQ = new MessageSenderGatewayActiveMQ("");
        this.messageSenderGatewayActiveMQ.initProducerQueue("DeviceStatus");

        this.gson = new Gson();
        this.heaterManager = heaterManager;
    }

    //todo: command or poll?
    private void heartbeatPollReceived(Message message){
        try {
            //Prepare heartbeat data
            String correlationID = message.getJMSCorrelationID();
            String deviceStatus = gson.toJson(new DeviceStatus(heaterManager.getDeviceID(), "Operational"));

            //Send reply
            ActiveMQTextMessage reply = new ActiveMQTextMessage();
            reply.setText(deviceStatus);
            reply.setCorrelationId(correlationID);
            this.messageSenderGatewayActiveMQ.sendMessageCorr(reply);

            System.out.println("Device #"+this.heaterManager.getDeviceID() + " received status poll");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void commandReceived(){

    }
}
