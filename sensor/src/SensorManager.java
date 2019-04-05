import messaging.models.SensorData;

import java.util.Timer;

public class SensorManager {
    private final int sensorID;
    private final ManagerGateway managerGateway;

    private SensorData currentSensorData;


    private Timer timer = new Timer();

    public SensorManager(int sensorID) {
        this.sensorID = sensorID;
        this.managerGateway = new ManagerGateway();
    }

    public void start(){
        int interval = 1500;

        timer.schedule(new Measurment(this), 0, interval);
        //todo gateway.init?
    }

    public void stop(){

    }

    /**
     * Send sensorData to queue if it's value is changed
     * @param sensorData
     */
    public void newSensorData(SensorData sensorData){
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
