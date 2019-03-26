/**
 * TODO
 */
package com.message.rabbit.queue;

import com.message.rabbit.RabbitFactory;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

public class Producer {

    private final static String QUEUE_NAME = "wendu_test_queue";

    public static void main(String[] argv) throws Exception {
        Channel channel = RabbitFactory.getChannel();

        ExecutorService executorService = Executors.newCachedThreadPool();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        executorService.execute(
                new Runnable() {
                    public void run() {
                        try {

                            while (true) {
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd  HH:mm:ss");
                                String message = "Hello World!" + format.format(new Date());
                                try {
                                    channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
                                    Thread.sleep(3000);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("Producer [x] Sent '" + message + "'");


                                //TODO
                                channel.basicPublish("", QUEUE_NAME,
                                        new AMQP.BasicProperties.Builder()
                                                .contentType("text/plain")
                                                .deliveryMode(2)
                                                .priority(1)
                                                .userId("zhouhualing")
                                                .build(),
                                        "fuckubody".getBytes());
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();

                        } finally {
                            try {
                                channel.close();
                                channel.getConnection().close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (TimeoutException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }
}
