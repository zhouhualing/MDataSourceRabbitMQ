/**
 * TODO
 * 
 */
package com.message.rabbit.exchange;

/**
 * @author hualing
 *
 */

import com.message.rabbit.RabbitFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.*;

import java.io.IOException;

public class ReceiveLogs1 {
	private static final String EXCHANGE_NAME = "logs";

	public static void main(String[] argv) throws Exception {
		Channel channel = RabbitFactory.getChannel();

		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, EXCHANGE_NAME, "");

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + message + "'");
			}
		};
		channel.basicConsume(queueName, true, consumer);

		if(channel != null){
			channel.close();
			if(channel.getConnection()!= null){
				channel.getConnection().close();
			}
		}
	}
}
