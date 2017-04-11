package com.hero;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import com.messaging.MessageHandler;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.StringRpcServer;
import com.repository.RPCServer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Initializer implements WebApplicationInitializer {

    public Initializer() {
    }

    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    public void onStartup(ServletContext servletContext)
            throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebAppConfig.class);
        servletContext.addListener(new ContextLoaderListener(ctx));

        servletContext.addListener(new JmsListener());

        ctx.setServletContext(servletContext);
        servletContext.addFilter("simpleCorsFilter", "com.hero.SimpleCORSFilter");

        Dynamic servlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME,
                new DispatcherServlet(ctx));
//		String[] arr={"/test/*","/hero/*"};	
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);


    }


}

class JmsListener implements ServletContextListener, Runnable {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Thread mapper;
        mapper = new Thread((Runnable) this);
        mapper.setPriority(Thread.MIN_PRIORITY);
        mapper.start();


       /* System.out.println("mmessaging context initialised");

        try {
            String hostName = "localhost";
            //int portNumber = (args.length > 1) ? Integer.parseInt(args[1]) : AMQP.PROTOCOL.PORT;

            ConnectionFactory connFactory = new ConnectionFactory();
            connFactory.setHost(hostName);
            //  connFactory.setPort(portNumber);
            Connection conn = connFactory.newConnection();
            final Channel ch = conn.createChannel();

            MessageHandler handler = new MessageHandler(ch, "rpc");


          handler.mainloop();
        } catch (Exception ex) {
            System.err.println("Main thread caught exception: " + ex);
            ex.printStackTrace();
            System.exit(1);
        }*/
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void run() {
        RPCServer.testIt();

    }
}
