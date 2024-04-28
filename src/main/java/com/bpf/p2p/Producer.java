package com.bpf.p2p;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {
    private static  final String QUEUE_NAME = "testQueue";

    public static void main(String[] args) {
        //ConnectionFactory : 连接工厂，JMS用它创建连接
        ConnectionFactory connectionFactory = null;
        //Connection : 客户端和JMS系统之间建立的连接
        Connection connection = null;
        //Session : 一个发送或接受消息的线程，操作消息的接口
        Session session = null;
        //Destination : 消息目的地，消息发送给谁
        Destination destination = null;
        //MessageProducer : 消息生产者
        MessageProducer producer = null;

        try {
            //step 1 构造ConnectionFactory实例对象，需要填入 用户名、密码、连接地址
            connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD,"tcp://localhost:61616");
            //step 2 连接工厂创建连接对象
            connection = connectionFactory.createConnection();
            //step 3 启动
            connection.start();
            //step 4 获取操作连接
            /*
            * 第一个参数：是否设置事务 true or false。 如果设置了true，第二个参数忽略，并且需要commit()才能执行
            * 第二个参数：acknowledge模式
            * AUTO_ACKNOWLEDGE：自动确认，客户端发送和接收消息不需要做额外的工作。不管消息是否被正常处理。默认
            * CLIENT_ACKNOWLEDGE：客户端确认，客户端接收到消息后，必须手动调用acknowledeg()方法，JMS服务器才会删除消息。
            * DUPS_OK_ACKNOWLEDGE：允许重复的确认模式。
            * */
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            //step 5 创建一个队列到目的地
            destination = session.createQueue(QUEUE_NAME);
            //step 6 在目的地创建一个生产者
            producer = session.createProducer(destination);
            //step 7 生产者设置不持久化
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            //step 8 生产者发送消息，具体的业务逻辑
            SchoolRecord record = new SchoolRecord();
            record.setChineseScore(120);
            record.setEnglishScore(51.5f);
            record.setMathScore(60f);
            sendMessage(session, producer, record);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(null != connection){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void sendMessage(Session session, MessageProducer producer, SchoolRecord record) throws JMSException {
        ObjectMessage message = session.createObjectMessage(record);
        producer.send(message);
        System.out.println("produce bean ------>" + record);
    }



}
