package com.nogoon.samples.amqp;

import org.junit.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RabbitMQSpringTest {
	@Test
	public void publisherTest() throws Exception {
		
        RabbitTemplate rabbitTemplate = getContext().getBean(RabbitTemplate.class);
        while(true){
            String msg = "Spring Sent at:" + System.currentTimeMillis();
            System.out.println(msg);
            byte[] body = msg.getBytes("UTF-8");
            rabbitTemplate.send(new Message(body, new MessageProperties()));
            Thread.sleep(1000);
        }
	}
	
	@Test
	public void receiverTest() throws Exception {
		
        RabbitTemplate rabbitTemplate = getContext().getBean(RabbitTemplate.class);
        while (true) {
            Message response = rabbitTemplate.receive();
            if (response != null) {
                System.out.println("Spring Recieved:->" + new String(response.getBody(), "UTF-8"));
            }
            Thread.sleep(500);
        }
	}
	
	public ApplicationContext getContext() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext_test.xml");
		return ctx;
	}
}
