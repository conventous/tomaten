package messaging;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MessageReceiverGatewayActiveMQ {
    private ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);

    private Connection connection;
    private Session session;
    private Destination receiveDestination;
    private MessageConsumer consumer;

    public MessageReceiverGatewayActiveMQ(String queue) {
    }

    public void initConsumerQueue(String queue){
        try {
            connection = connectionFactory.createConnection();
            //connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); //todo aanpassen voor testen DLQ mogelijk

            receiveDestination = session.createQueue(queue);

            consumer = session.createConsumer(receiveDestination);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void initConsumerTopic(String topic){

        try {
            connection = connectionFactory.createConnection();
            //connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); //todo aanpassen voor testen DLQ mogelijk

            receiveDestination = session.createTopic(topic);

            consumer = session.createConsumer(receiveDestination);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void setListener(MessageListener messageListener){
        try {
            this.consumer.setMessageListener(messageListener);
            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
