package messaging.models;

import java.util.Date;

public abstract class SensorData {
    private final int sensorID;
    private final Date date;

    public SensorData(int sensorID, Date date) {
        this.sensorID = sensorID;
        this.date = date;
    }

    public int getSensorID() {
        return sensorID;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "SensorData{" +
                "sensorID=" + sensorID +
                ", date=" + date +
                '}';
    }
}
