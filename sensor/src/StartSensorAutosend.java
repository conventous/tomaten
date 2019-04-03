import messaging.models.SensorData;

import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class StartSensorAutosend {
    public static void main(String[] args) {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            ManagerGateway managerGateway = new ManagerGateway();

            @Override
            public void run() {
                int minTemp = 20;
                int maxTemp = 35;
                int randomTemperature =  ThreadLocalRandom.current().nextInt(minTemp, maxTemp+ 1);

                SensorData sensorData = new SensorData(9001, randomTemperature, new Date());
                System.out.println(sensorData.toString());

                managerGateway.sendSensorData(sensorData);
            }
        }, 0, 1500);
    }
}