package com.owen.spring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class Producer
{
	public static void main(String[] args)
	{
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("classpath:application-context.xml");
		JmsTemplate template = (JmsTemplate) context.getBean("jmsTemplate");
		ActiveMQQueue queue = (ActiveMQQueue) context.getBean("queueDestination");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			String input;
			try
			{
				if((input = reader.readLine()).equals("exit"))
					break;
				else
				{
					template.send(queue, new MessageCreator()
					{
						@Override
						public Message createMessage(Session arg0) throws JMSException
						{
							return arg0.createTextMessage(input);
						}
					});
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
