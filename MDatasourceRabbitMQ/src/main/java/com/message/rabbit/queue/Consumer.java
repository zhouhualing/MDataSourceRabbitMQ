package com.message.rabbit.queue;

import com.message.rabbit.RabbitFactory;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {

	private final static String QUEUE_NAME = "wendu_test_queue";

	public static void main(String[] argv) throws Exception {
		Channel channel = RabbitFactory.getChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println("Consumer [*] Waiting for messages. To exit press CTRL+Consumer");

		com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println("Consumer [x] Received '" + message + "'");
			}
		};
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}
}
