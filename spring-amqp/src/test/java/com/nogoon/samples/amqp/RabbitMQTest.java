package com.nogoon.samples.amqp;

import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

public class RabbitMQTest {
	
	@Test
	public void publisherTest() throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("guest");
		factory.setPassword("guest");
		factory.setHost("localhost");
		factory.setPort(5672);
		factory.setVirtualHost("/");
        
        //factory.setUsername(uri.getUserInfo().split(":")[0]);
        //factory.setPassword(uri.getUserInfo().split(":")[1]);
        //factory.setHost(uri.getHost());
        //factory.setPort(uri.getPort());
        //factory.setVirtualHost(uri.getPath().substring(1));
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		String exchangeName = "nogoon-exchange";
        String queueName = "nogoon-queue";
        String routingKey = "nogoon-key";
        channel.exchangeDeclare(exchangeName, "direct", true);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);
        
        while (true) {
            String msg = "Sent at:" + System.currentTimeMillis();
            byte[] body = msg.getBytes("UTF-8");
            channel.basicPublish(exchangeName, routingKey, null, body);
            System.out.println("Sent:->" + msg); 
            Thread.sleep(1000);
        }   

	}
	
	@Test
	public void receiverTest() throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("guest");
		factory.setPassword("guest");
		factory.setHost("localhost");
		factory.setPort(5672);
		factory.setVirtualHost("/");
        
        //factory.setUsername(uri.getUserInfo().split(":")[0]);
        //factory.setPassword(uri.getUserInfo().split(":")[1]);
        //factory.setHost(uri.getHost());
        //factory.setPort(uri.getPort());
        //factory.setVirtualHost(uri.getPath().substring(1));
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		String exchangeName = "nogoon-exchange";
        String queueName = "nogoon-queue";
        String routingKey = "nogoon-key";
        channel.exchangeDeclare(exchangeName, "direct", true);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);
        
        while (true) {
            GetResponse response = channel.basicGet(queueName,true);
            if (response != null) {
                System.out.println("Recieved:->" + new String(response.getBody(), "UTF-8"));
            }
            Thread.sleep(500);
        }
		
	}
}
