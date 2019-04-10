package gateway;

import regulator.Device;

import java.util.ArrayList;
import java.util.Collection;

public class Router {
    private Collection<Device> devices;

    public Router() {
        initDevices();
    }

    public void initDevices(){
        this.devices = new ArrayList<>();

        //todo maybe dynamic router? of juist niet?
        this.devices.add(new Device(1, Device.DeviceType.AIRCO));
        this.devices.add(new Device(2, Device.DeviceType.HEATER));
        this.devices.add(new Device(3, Device.DeviceType.HEATER));
    }


    public Collection<Device> getDevices() {
        return devices;
    }

    public void setDevices(Collection<Device> devices) {
        this.devices = devices;
    }
}
