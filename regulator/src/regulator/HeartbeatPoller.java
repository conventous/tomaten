package regulator;

import gateway.DeviceGateway;

import java.util.TimerTask;

public class HeartbeatPoller extends TimerTask {
    private final DeviceGateway deviceGateway;

    public HeartbeatPoller(DeviceGateway deviceGateway) {
        this.deviceGateway = deviceGateway;
    }

    @Override
    public void run() {
        this.deviceGateway.broadcastHeartbeatPoll();
    }
}
