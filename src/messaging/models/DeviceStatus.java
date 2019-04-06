package messaging.models;

public class DeviceStatus {
    private final int deviceID;
    private String status;

    public DeviceStatus(int deviceID, String status) {
        this.deviceID = deviceID;
        this.status = status;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DeviceStatus{" +
                "deviceID=" + deviceID +
                ", status='" + status + '\'' +
                '}';
    }
}
