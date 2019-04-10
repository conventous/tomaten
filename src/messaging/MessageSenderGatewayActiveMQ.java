package messaging;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;

public class MessageSenderGatewayActiveMQ {
    private ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);

    private Connection connection;
    private Session session;
    private Destination sendDestination;
    private MessageProducer producer;

    public MessageSenderGatewayActiveMQ(String queue) {
    }

    public void initProducerQueue(String queue){
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

    public void initProducerTopic(String topic){
        try {
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            sendDestination = session.createTopic(topic);

            producer = session.createProducer(sendDestination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public String sendMessage(String message){
        try {
            ActiveMQTextMessage msg = new ActiveMQTextMessage();
            msg.setText(message);

            this.producer.send(msg);

            return msg.getJMSCorrelationID();
        } catch (JMSException e) {
            e.printStackTrace();
            return "ERROR CORR ID";
        }
    }

    public void sendMessage(Message message, String queue){
        try {
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            sendDestination = session.createQueue(queue);
            producer = session.createProducer(sendDestination);

            this.producer.send(sendDestination, message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message, String queue){
        try {
            ActiveMQTextMessage msg = new ActiveMQTextMessage();
            msg.setText(message);

            this.producer.send(session.createQueue(queue), msg);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageCorr(Message message){
        try {
            this.producer.send(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
