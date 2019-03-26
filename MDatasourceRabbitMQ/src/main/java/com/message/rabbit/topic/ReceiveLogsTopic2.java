/**
 * TODO
 * 
 */
package com.message.rabbit.topic;

/**
 * @author hualing
 *
 */

import com.message.rabbit.RabbitFactory;
import com.rabbitmq.client.*;

import java.io.IOException;

public class ReceiveLogsTopic2 {

	private static final String EXCHANGE_NAME = "topic_logs";
	 
	public static void main(String[] argv) throws Exception {
		Channel channel = RabbitFactory.getChannel();
		Connection connection = channel.getConnection();
//		����һ��ƥ��ģʽ�Ľ�����
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
		String queueName = channel.queueDeclare().getQueue();
		// ·�ɹؼ���
		String[] routingKeys = new String[]{"*.*.rabbit", "lazy.#"};
//		��·�ɹؼ���
		for (String bindingKey : routingKeys) {
			channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
			System.out.println("ReceiveLogsTopic2 exchange:"+EXCHANGE_NAME+", queue:"+queueName+", BindRoutingKey:" + bindingKey);
		}

		System.out.println("ReceiveLogsTopic2 [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println("ReceiveLogsTopic2 [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
			}
		};
		channel.basicConsume(queueName, true, consumer);
	}
}