import temp.SensorManagerTemp;
import water.SensorManagerWater;

public class StartSensors {
    public static void main(String[] args) {

        SensorManagerTemp sensorManagerTemp = new SensorManagerTemp(9001);
        sensorManagerTemp.start();

        SensorManagerWater sensorManagerWater = new SensorManagerWater(42);
        sensorManagerWater.start();

//        Scanner command = new Scanner(System.in);
//        gateway.ManagerGateway managerGateway = new gateway.ManagerGateway();
//
//        System.out.println("Send temperature in C*: ");
//        boolean running = true;
//
//        while(running){
//
//            switch(command.nextLine()){
//
//                case "send":
//                    // managerGateway.sendSensorData("Hello world");
//                    System.out.println("Message send!");
//                    break;
//
//                case "exit":
//                    System.out.println("Application Closed");
//                    running = false;
//                    break;
//
//                default:
//                    System.out.println("Command not recognized!");
//                    break;
//            }
//        }
//        command.close();
    }
}