package gateway;

//todo https://www.enterpriseintegrationpatterns.com/patterns/messaging/DynamicRouter.html

import com.google.gson.Gson;
import messaging.MessageSenderGatewayActiveMQ;
import messaging.models.StatusPoll;

public class DeviceGateway {
    private MessageSenderGatewayActiveMQ messageSenderGatewayActiveMQ;

    private final Gson gson;

    public DeviceGateway() {
        this.messageSenderGatewayActiveMQ = new MessageSenderGatewayActiveMQ("DeviceStatus");
        this.messageSenderGatewayActiveMQ.initProducerTopic("DeviceStatus");

        this.gson = new Gson();
    }

    public void broadcastHeartbeatPoll(){
        String heartBeatPollJOSN = gson.toJson(new StatusPoll());
        this.messageSenderGatewayActiveMQ.sendMessage(heartBeatPollJOSN);
        System.out.println("Polling for heartbeats");
    }

    //todo receive heartbeats with corrID
}
