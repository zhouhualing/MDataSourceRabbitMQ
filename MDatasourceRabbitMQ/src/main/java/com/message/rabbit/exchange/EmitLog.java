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
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLog {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {
        Channel channel = RabbitFactory.getChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

		for(int i = 0 ; i < 500000; i++){
			String message = "Hello World! " + i;
			 channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
		     System.out.println(" [x] Sent '" + message + "'");
		     Thread.sleep(2000);
		}
		if(channel != null) {
            channel.close();
            if (channel.getConnection() != null)
                channel.getConnection().close();
        }
    }
}
