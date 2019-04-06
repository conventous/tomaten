package temp;

import gateway.ManagerGateway;
import messaging.models.SensorDataTemp;

import java.util.Timer;

public class SensorManagerTemp {
    private final int sensorID;
    private final ManagerGateway managerGateway;

    private SensorDataTemp currentSensorData;

    private Timer timer = new Timer();

    public SensorManagerTemp(int sensorID) {
        this.sensorID = sensorID;
        this.managerGateway = new ManagerGateway("SensorQueueTemp");
    }

    public void start(){
        int interval = 1500;

        timer.schedule(new MeasurmentTemp(this), 0, interval);
        //todo gateway.init?
    }

    public void stop(){

    }

    /**
     * Send sensorData to queue if it's value is changed
     * @param sensorData
     */
    public void newSensorData(SensorDataTemp sensorData){
        if(this.currentSensorData != null
                && currentSensorData.getCelsius() != sensorData.getCelsius()){
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
