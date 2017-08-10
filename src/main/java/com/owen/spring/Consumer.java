package com.owen.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

public class Consumer
{
	public static void main(String[] args)
	{
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("classpath:application-context.xml");
		DefaultMessageListenerContainer container = 
				(DefaultMessageListenerContainer) context.getBean("jmsContainer");
		container.start();
	}
}
