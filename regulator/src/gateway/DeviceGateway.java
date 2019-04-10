package gateway;

//todo https://www.enterpriseintegrationpatterns.com/patterns/messaging/DynamicRouter.html

import com.google.gson.Gson;
import messaging.MessageReceiverGatewayActiveMQ;
import messaging.MessageSenderGatewayActiveMQ;
import messaging.models.StatusPoll;
import javax.jms.Message;

import org.apache.activemq.command.ActiveMQTextMessage;
import regulator.Device;

import javax.jms.JMSException;
import javax.jms.MessageNotWriteableException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeviceGateway {
    private MessageSenderGatewayActiveMQ messageSenderGatewayActiveMQ;
    private MessageReceiverGatewayActiveMQ messageReceiverGatewayActiveMQ;

    private Aggregator aggregator;
    private Router router;

    private final Gson gson;

    public DeviceGateway() {
        this.messageSenderGatewayActiveMQ = new MessageSenderGatewayActiveMQ("DeviceStatus");
        this.messageSenderGatewayActiveMQ.initProducerTopic("DeviceStatus");

        this.messageReceiverGatewayActiveMQ = new MessageReceiverGatewayActiveMQ("");
        this.messageReceiverGatewayActiveMQ.initConsumerQueue("DeviceStatus");
        this.messageReceiverGatewayActiveMQ.setListener(this::receiveHeartbeats);

        this.gson = new Gson();
        this.router = new Router();
    }

    public void broadcastHeartbeatPoll(){
        String heartBeatPollJOSN = gson.toJson(new StatusPoll()); //todo statuspoll met ID?

        //Construct message and get correlationID
        ActiveMQTextMessage message = new ActiveMQTextMessage();
        try {
            message.setText(heartBeatPollJOSN);
            message.setCorrelationId(UUID.randomUUID().toString());
        } catch (MessageNotWriteableException e) {
            e.printStackTrace();
        }
        String correlationID = message.getJMSCorrelationID();

        //Did all devices replied to the last heartbeat?
        if(aggregator != null){
            List<Device> offlineDevices = aggregator.notRepliedToHeartbeat();

            if(aggregator.notRepliedToHeartbeat().size() > 0){
                for(Device device : offlineDevices){
                    System.out.println("Device#"+device.getDeviceID() + " did not reply to last heartbeat");
                }
            }
        }

        //Create new aggregrator with new CorrelationID
        aggregator = new Aggregator(correlationID, this.router); //todo message if not all messages received

        //Send poll to all devices in router
        for(Device device : router.getDevices()){
            this.messageSenderGatewayActiveMQ.sendMessage(message, device.getCommandChannel());
            System.out.println("Polling queue " +device.getCommandChannel() + " for heartbeats");
        }
    }

    public void receiveHeartbeats(Message message){
        if(this.aggregator != null){
            this.aggregator.newMessage(message);
        }
    }

    public void activateHeaters(){
        for(Device device : router.getDevices()){
            if(device.deviceType.equals(Device.DeviceType.HEATER)){
                this.messageSenderGatewayActiveMQ.sendMessage("Turn on heater pls", device.getCommandChannel());
            }
        }
    }
}
