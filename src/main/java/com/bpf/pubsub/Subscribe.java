package com.bpf.pubsub;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Subscribe extends Thread {
    private static final String TOPIC_NAME = "testTopic";
    private static final String USER = "dev1";
    private static final String PASSWORD = "Dev1@aecc.cn";
    private static final String BROKER_URL = "tcp://47.115.63.73:61616";


    @Override
    public void run() {
        {
            ConnectionFactory connectionFactory = null;
            Connection connection = null;
            Session session = null;
            Destination destination = null;
            MessageConsumer consumer = null;
            final String name = Thread.currentThread().getName();
            try {
                connectionFactory = new ActiveMQConnectionFactory(Subscribe.USER,Subscribe.PASSWORD,Subscribe.BROKER_URL);
                connection = connectionFactory.createConnection();
                connection.start();
                session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                destination = session.createTopic(Subscribe.TOPIC_NAME);
                 	//destination = session.createQueue("garine-queue?consumer.prefetchSize=10");
                consumer = session.createConsumer(destination);
                
                
                //接受文本消息，阻塞等待
          /*      for (int i=1;i<=2000;i++){
                	
                    TextMessage textMessage = (TextMessage) consumer.receive();
                    System.out.println(name + "收到消息：" + textMessage.getText());
                }
                session.close();*/

                
                consumer.setMessageListener(new MessageListener() {
                    @Override
                    public void onMessage(Message message) {
                        try {
                            System.out.println( name + "收到消息：" + ((TextMessage) message).getText() );
//                            Thread.sleep(2000);
                        } catch (JMSException e) {
                            e.printStackTrace();
                        } 
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }




}
