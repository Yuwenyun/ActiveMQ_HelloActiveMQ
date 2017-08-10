package com.owen.spring;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMsgListener implements MessageListener
{
	@Override
	public void onMessage(Message arg0)
	{
		TextMessage msg = (TextMessage)arg0;
		System.out.println(msg.toString());
	}
}
