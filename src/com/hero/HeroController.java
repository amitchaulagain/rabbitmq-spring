package com.hero;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import com.messaging.MessageHandler;
import com.rabbitmq.client.*;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.User;
import com.repository.IUser;

@Controller
public class HeroController {

    HeroController() {
        System.out.println("It is it");

    }


    private static final String EXCHANGE_NAME = "TRANSPORTER";
    private static final String ROUTING_KEY = "rms.financial";

    @Resource
    IUser userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewAboutUsPage() {
        return "index";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    @ResponseBody
    public List<User> hero() {

        return userRepository.findAll();
    }

    @RequestMapping(value = "/allusers", method = RequestMethod.GET)
    @ResponseBody
    public User allUsers() {
        User u = new User();
        u.setUsername("aa");
        u.setPassword("dsfhfd");

        return u;
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public
    @ResponseBody
    String createTest(@RequestBody User u) throws JsonGenerationException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();


        return mapper.writeValueAsString("Successfully created");

    }


    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public
    @ResponseBody
    String send() throws JsonGenerationException, JsonMappingException, IOException {

        ObjectMapper mapper = new ObjectMapper();


        Connection connection = null;
        Channel channel = null;
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");

            connection = factory.newConnection();
            channel = connection.createChannel();

            // AMQP.Exchange.DeclareOk ok=channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
            // channel.queueDeclare("first",false,false,false,null);

            // String routingKey = getRouting(argv);
            String message = " From producer with routing key" + ROUTING_KEY;

            Map<String, Object> headers = new HashMap<String, Object>();
            headers.put("latitude", 51.5252949);
            headers.put("longitude", -0.0905493);

            AMQP.BasicProperties customHeaders = new AMQP.BasicProperties().builder().headers(headers).build();


            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, (AMQP.BasicProperties) customHeaders, message.getBytes("UTF-8"));


            System.out.println(" [x] Sent '" + ROUTING_KEY + "':'" + message + "'");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception ignore) {
                }
            }
        }
        return mapper.writeValueAsString("Successfully created");
    }


    @RequestMapping(value = "/rpc", method = RequestMethod.GET)
    public
    @ResponseBody
    String rpc() throws JsonGenerationException, JsonMappingException, IOException {

        ObjectMapper mapper = new ObjectMapper();


        try {
            String hostName = "localhost";
            //int portNumber = (args.length > 1) ? Integer.parseInt(args[1]) : AMQP.PROTOCOL.PORT;

            ConnectionFactory connFactory = new ConnectionFactory();
            connFactory.setHost(hostName);
            //  connFactory.setPort(portNumber);
            Connection conn = connFactory.newConnection();
            final Channel ch = conn.createChannel();

            MessageHandler handler = new MessageHandler(ch, "rpc");
           /* handler.handleStringCall()*/

           /* ch.queueDeclare("Hello", false, false, false, null);
            StringRpcServer server = new StringRpcServer(ch, "Hello") {
                @Override
                public String handleStringCall(String request) {
                    System.out.println("Got request: " + request);
                    return "Hello, " + request + "!";
                }
            };*/
            handler.mainloop();
        } catch (Exception ex) {
            System.err.println("Main thread caught exception: " + ex);
            ex.printStackTrace();
            System.exit(1);
        }
        return mapper.writeValueAsString("Successfully created");
    }


    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public
    @ResponseBody
    String data() throws JsonGenerationException, JsonMappingException, IOException, TimeoutException {
        RpcClient service = null;


        try {
            String request = "Rabbit";
            String hostName = "localhost";

            ConnectionFactory cfconn = new ConnectionFactory();
            cfconn.setHost(hostName);
            //  cfconn.setPort(portNumber);
            Connection conn = cfconn.newConnection();
            Channel ch = conn.createChannel();
            service = new RpcClient(ch, "TRANSPORTER", "rpcKey");


            System.out.println("comeon");

        } catch (Exception e) {
            System.err.println("Main thread caught exception: " + e);
            e.printStackTrace();
            System.exit(1);
        }
        return service.stringCall("getCustomer");
    }
}







/*



*/
