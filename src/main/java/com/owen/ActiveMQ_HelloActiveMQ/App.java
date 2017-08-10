package com.owen.ActiveMQ_HelloActiveMQ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL;
	private static final String QUEUE_NAME = "Hello_Owen";

	public static void main( String[] args )
	{
		ConnectionFactory factory = new ActiveMQConnectionFactory();
		try
		{
			Connection conn = factory.createConnection();
			// connection has to be started, or no msg will be sent
			conn.start();

			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(QUEUE_NAME);

			MessageProducer producer = session.createProducer(destination);
			TextMessage msg = session.createTextMessage();

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			while(true)
			{
				String input;
				if((input = reader.readLine()).equals("exit"))
				{
					break;
				}
				else
				{
					msg.setText(input);
					producer.send(msg);
				}
			}
			
			conn.close();
		}
		catch (JMSException | IOException e)
		{
			e.printStackTrace();
		}
	}
}
