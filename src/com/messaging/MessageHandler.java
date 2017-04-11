package com.messaging;

/**
 * Created by amit on 4/5/17.
 */

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.StringRpcServer;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class MessageHandler extends StringRpcServer {


    public MessageHandler(Channel channel, String queueName) throws IOException {

        super(channel, queueName);
       // channel.queueBind(queueName, "TRANSPORTER", "#.financial.#");
    }

    @Override
    public String handleStringCall(String request) {

        System.out.println("Got request: " + request);
        String customerJson = null;
        if (request.equals("getCustomer")) {

            Customer customer = new Customer(1, "Amit Chaulagain");
            ObjectMapper mapper = new ObjectMapper();

            try {
                customerJson = mapper.writeValueAsString(customer);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return customerJson;
    }
}