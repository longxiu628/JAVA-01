import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;

public class ActivemqTest {

    public static void main(String[] args) {
        final ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        final ActiveMQDestination queueDestination = new ActiveMQQueue("test1.queue");
        final ActiveMQDestination topicDestination = new ActiveMQTopic("test1.topic");
        new Thread(new Tester(queueDestination, factory)).start();
        new Thread(new Tester(topicDestination, factory)).start();
    }

    static class Tester implements Runnable {
        ActiveMQDestination destination;
        ActiveMQConnectionFactory factory;
        public Tester(final ActiveMQDestination destination, final ActiveMQConnectionFactory factory) {
            this.destination = destination;
            this.factory = factory;
        }
        public void run() {
            ActiveMQConnection conn = null;
            Session session = null;
            try {
                conn = (ActiveMQConnection) factory.createConnection();
                conn.start();
                session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageConsumer consumer = session.createConsumer(destination);
                MessageListener listener = new MessageListener() {
                    public void onMessage(Message message) {
                        try {
                            System.out.println(destination.getDestinationTypeAsString() + " receive message：" + ((TextMessage)message).getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                };
                consumer.setMessageListener(listener);
                MessageProducer producer = session.createProducer(destination);
                while (true) {
                    TextMessage message = session.createTextMessage("hello");
                    producer.send(message);
                    Thread.sleep(2000);
                }
            } catch (JMSException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    session.close();
                    conn.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
