import java.util.Scanner;

public class StartSensor {
    public static void main(String[] args) {

        SensorManager sensorManager = new SensorManager(9001);
        sensorManager.start();

//        Scanner command = new Scanner(System.in);
//        ManagerGateway managerGateway = new ManagerGateway();
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