package messaging;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;

public class MessageSenderGatewayActiveMQ {
    private ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

    private Connection connection;
    private Session session;
    private Destination sendDestination;
    private MessageProducer producer;

    public MessageSenderGatewayActiveMQ(String queue) {
        try {
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); //todo aanpassen voor testen DLQ mogelijk

            sendDestination = session.createQueue(queue);

            producer = session.createProducer(sendDestination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message){
        try {
            ActiveMQTextMessage msg = new ActiveMQTextMessage();
            msg.setText(message);

            this.producer.send(msg);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
