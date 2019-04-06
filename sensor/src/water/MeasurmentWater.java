package water;

import messaging.models.SensorDataWater;

import java.util.Date;
import java.util.TimerTask;

public class MeasurmentWater extends TimerTask {
    private final SensorManagerWater sensorManagerWater;

    public MeasurmentWater(SensorManagerWater sensorManagerWater) {
        this.sensorManagerWater = sensorManagerWater;
    }

    @Override
    public void run() {
        SensorDataWater sensorData = this.generateRandomSensorData();
        this.sensorManagerWater.newSensorData(sensorData);
    }

    private SensorDataWater generateRandomSensorData(){
        SensorDataWater sensorData = new SensorDataWater(9001, new Date(), "WATER LEVEL TODO RANDOM");
        System.out.println("Generated random sensorDataWater: " + sensorData.toString());
        return sensorData;
    }
}
