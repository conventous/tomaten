package regulator;

public class Device {
    public int deviceID;
    public DeviceType deviceType;

    public Device(int deviceID, DeviceType deviceType) {
        this.deviceID = deviceID;
        this.deviceType = deviceType;
    }

    public String getCommandChannel(){
        return "device#"+deviceID;
    }

    public enum DeviceType {
        HEATER,
        SPRINKLER,
        AIRCO
    }

    public int getDeviceID() {
        return deviceID;
    }
}




