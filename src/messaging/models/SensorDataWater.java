package messaging.models;

import java.util.Date;

public class SensorDataWater extends SensorData {
    private String humidity;

    public SensorDataWater(int sensorID, Date date, String humidity) {
        super(sensorID, date);
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "SensorDataWater{" +
                "humidity='" + humidity + '\'' +
                '}';
    }
}
