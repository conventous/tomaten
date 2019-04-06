package messaging.models;

import java.util.Date;

public class SensorDataTemp extends SensorData{
    private final int celsius;

    public SensorDataTemp(int sensorID, Date date, int celsius) {
        super(sensorID, date);
        this.celsius = celsius;
    }

    public int getCelsius() {
        return celsius;
    }

    @Override
    public String toString() {
        return "SensorDataTemp{" +
                "celsius=" + celsius +
                '}';
    }
}
