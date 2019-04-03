package messaging;

import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.ConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

import javax.jms.*;
import javax.naming.NamingException;

public class MessageSenderGateway {
    private ConnectionFactory connectionFactory;

    private Connection connection;
    private Session session;
    private Destination sendDestination;
    private MessageProducer producer;

    public MessageSenderGateway(String queue) {
        //todo dit stuk bij AppGateway want is app specifiek?
        Properties props = new Properties();
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
        props.put(("queue." + queue), queue);


        try{
            Context jndiContext = new InitialContext(props);
            connectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");

            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            sendDestination = (Destination) jndiContext.lookup(queue);
            producer = session.createProducer(sendDestination);

        } catch (NamingException | JMSException e) {
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
