import messaging.models.SensorData;

import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class Measurment extends TimerTask {
    private final SensorManager sensorManager;

    public Measurment(SensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    @Override
    public void run() {
        SensorData sensorData = this.generateRandomSensorData();
        this.sensorManager.newSensorData(sensorData);
    }

    private SensorData generateRandomSensorData(){
        int minTemp = 20;
        int maxTemp = 35;
        int randomTemperature =  ThreadLocalRandom.current().nextInt(minTemp, maxTemp+ 1);

        SensorData sensorData = new SensorData(9001, randomTemperature, new Date());
        System.out.println("Generated random sensorData: " + sensorData.toString());
        return sensorData;
    }
}
