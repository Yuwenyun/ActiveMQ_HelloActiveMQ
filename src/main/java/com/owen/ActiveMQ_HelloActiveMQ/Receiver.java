package com.owen.ActiveMQ_HelloActiveMQ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver
{
	private static final String QUEUE_NAME = "Hello_Owen";
	
	public static void main(String[] args)
	{
		ConnectionFactory factory = new ActiveMQConnectionFactory();
		try
		{
			Connection conn = factory.createConnection();
			// connection has to be started, or no msg will be received
			conn.start();
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Destination destination = session.createQueue(QUEUE_NAME);
			MessageConsumer consumer = session.createConsumer(destination);
			
			while(true)
			{
				TextMessage msg = (TextMessage) consumer.receive(5000);
				System.out.println("Received Msg: " + msg);
			}
		}
		catch (JMSException e)
		{
			e.printStackTrace();
		}
	}
}
