package water;

import gateway.ManagerGateway;
import messaging.models.SensorDataWater;

import java.util.Timer;

public class SensorManagerWater {
    private final int sensorID;
    private final ManagerGateway managerGateway;

    private SensorDataWater currentSensorData;

    private Timer timer = new Timer();

    public SensorManagerWater(int sensorID) {
        this.sensorID = sensorID;
        this.managerGateway = new ManagerGateway("SensorQueueWater");
    }

    public void start(){
        int interval = 1500;

        timer.schedule(new MeasurmentWater(this), 0, interval);
        //todo gateway.init?
    }

    public void stop(){

    }

    public void newSensorData(SensorDataWater sensorData){
        if(sensorData != null){
            //todo value changed check
            this.managerGateway.sendSensorData(sensorData);
        }
        else{
            this.managerGateway.sendSensorData(sensorData);
        }

        this.currentSensorData = sensorData;
    }

    public int getSensorID() {
        return sensorID;
    }
}
