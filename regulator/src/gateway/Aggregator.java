package gateway;


import com.google.gson.Gson;
import messaging.models.DeviceStatus;
import org.apache.activemq.command.ActiveMQTextMessage;
import regulator.Device;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.*;

public class Aggregator {
    private String correlationID;

    private Gson gson;
    private Router router;

    private Map<Integer, Boolean> devicesReplied; //todo replace Integer with device

    public Aggregator(String correlationID, Router router) {
        this.correlationID = correlationID;
        this.router = router;

        this.gson = new Gson();
        this.devicesReplied = new HashMap<>();

        for(Device device : this.router.getDevices()){
            this.devicesReplied.put(device.deviceID, false);
        }
    }

    public void newMessage(Message message){
        try {
            if(message.getJMSCorrelationID() != null){
                if(correlationID.equals(message.getJMSCorrelationID())){
                    //Get device status reply from message
                    ActiveMQTextMessage textMessage = (ActiveMQTextMessage)message;
                    DeviceStatus deviceStatus = this.gson.fromJson(textMessage.getText(), DeviceStatus.class);

                    //Set replied to true for this device
                    this.devicesReplied.put(deviceStatus.getDeviceID(), true);
                    System.out.println("Setting device#"+deviceStatus.getDeviceID()+ " replied to true");

                    if (everyoneReplied()){
                        System.out.println("everyone replied" +"\n"+"\n");
                    }
                }
                else {
                    System.out.println("Correlation ID does not match");
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public boolean everyoneReplied(){
        for(Boolean bool : this.devicesReplied.values()){
            if(!bool){
                return  false;
            }
        }

        return true;
    }

    public List<Device> notRepliedToHeartbeat(){
        ArrayList<Device> offlineDevices = new ArrayList<>();

        Iterator it = devicesReplied.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();

            if(!(boolean) pair.getValue()){
                for(Device device: this.router.getDevices()){
                    if(device.getDeviceID() == (int)pair.getKey()){
                        offlineDevices.add(device);
                        break;
                    }
                }
            }
        }

        return offlineDevices;
    }
}
