/**
 * TODO
 * 
 */
package com.message.rabbit.routing;

import com.message.rabbit.RabbitFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author hualing
 *
 */
public class RoutingSendDirect {

    private static final String EXCHANGE_NAME = "direct_logs";
 // ·�ɹؼ���
 	private static final String[] routingKeys = new String[]{"info" ,"warning", "error"};
 	
    public static void main(String[] argv) throws Exception {

        Channel channel = RabbitFactory.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
//		������Ϣ
        for(String severity :routingKeys){
        	String message = "Send the message level:" + severity;
        	channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
        	System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
        }
    }
}