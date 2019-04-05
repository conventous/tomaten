package messaging.models;

import java.util.Date;

public class SensorData {
    private final int sensorID;
    private final int celsius;
    private final Date date;

    public SensorData(int sensorID, int celsius, Date date) {
        this.sensorID = sensorID;
        this.celsius = celsius;
        this.date = date;
    }

    public int getSensorID() {
        return sensorID;
    }

    public int getCelsius() {
        return celsius;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "SensorData{" +
                "sensorID=" + sensorID +
                ", celsius=" + celsius +
                ", date=" + date +
                '}';
    }
}
