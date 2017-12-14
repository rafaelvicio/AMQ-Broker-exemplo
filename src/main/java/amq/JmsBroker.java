package amq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.qpid.jms.JmsConnectionFactory;

public class JmsBroker {
	
	public static void main(String[] args) throws Exception {
        Connection connection = null;
        ConnectionFactory connectionFactory = new JmsConnectionFactory("amqp://localhost:5672");

        try {

            // Step 1. Create an amqp qpid 1.0 connection
            connection = connectionFactory.createConnection();

            // Step 2. Create a session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Step 3. Create a sender
            Queue queue = session.createQueue("exampleQueue");
            MessageProducer sender = session.createProducer(queue);

            // Step 4. send a few simple message
            sender.send(session.createTextMessage("Hello world 3"));

        } finally {
            if (connection != null) {
                // Step 9. close the connection
                connection.close();
            }
        }
    }
}
