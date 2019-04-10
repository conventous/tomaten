package regulator;

import gateway.DeviceGateway;
import gateway.SensorGateway;
import messaging.models.SensorData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Timer;

public class RegulationManager {
    private final SensorGateway sensorGateway;
    private final DeviceGateway deviceGateway;

    private Collection<SensorData> sensorData;

    private Timer timer = new Timer();

    public RegulationManager() {
        this.sensorGateway = new SensorGateway(this);
        this.deviceGateway = new DeviceGateway();

        this.sensorData = new ArrayList<>();

        timer.schedule(new HeartbeatPoller(this.deviceGateway), 0, 4500);
    }

    public void absorbdata(SensorData sensorData){
        this.sensorData.add(sensorData);
    }
}
