package temp;

import messaging.models.SensorDataTemp;

import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class MeasurmentTemp extends TimerTask {
    private final SensorManagerTemp sensorManager;

    public MeasurmentTemp(SensorManagerTemp sensorManager) {
        this.sensorManager = sensorManager;
    }

    @Override
    public void run() {
        SensorDataTemp sensorData = this.generateRandomSensorData();
        this.sensorManager.newSensorData(sensorData);
    }

    private SensorDataTemp generateRandomSensorData(){
        int minTemp = 20;
        int maxTemp = 35;
        int randomTemperature =  ThreadLocalRandom.current().nextInt(minTemp, maxTemp+ 1);

        SensorDataTemp sensorData = new SensorDataTemp(9001, new Date(), randomTemperature);
        System.out.println("Generated random sensorData: " + sensorData.toString());
        return sensorData;
    }
}
