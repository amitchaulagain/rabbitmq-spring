package com.messaging;

/**
 * Created by amit on 4/5/17.
 */

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class HelloServer {
    public static void main(String[] args) {
        try {
            String hostName = "localhost";
            //int portNumber = (args.length > 1) ? Integer.parseInt(args[1]) : AMQP.PROTOCOL.PORT;

            ConnectionFactory connFactory = new ConnectionFactory();
            connFactory.setHost(hostName);
            //  connFactory.setPort(portNumber);
            Connection conn = connFactory.newConnection();
            final Channel ch = conn.createChannel();

            MessageHandler handler= new MessageHandler(ch,"rpc");

            handler.mainloop();
        } catch (Exception ex) {
            System.err.println("Main thread caught exception: " + ex);
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
