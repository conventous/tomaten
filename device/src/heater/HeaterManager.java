package heater;

import gateway.RegulatorGateway;

public class HeaterManager {
    private final int deviceID;
    private final RegulatorGateway regulatorGateway;

    public HeaterManager(int deviceID) {
        this.deviceID = deviceID;
        this.regulatorGateway = new RegulatorGateway(this);
    }

    public int getDeviceID() {
        return deviceID;
    }
}
