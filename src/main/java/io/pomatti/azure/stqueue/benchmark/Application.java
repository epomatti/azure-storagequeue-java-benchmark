package io.pomatti.azure.stqueue.benchmark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

  private static Logger logger = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) throws Exception {
    Config.load();

    boolean initConsumer = Boolean.parseBoolean(Config.getProperty("app.init_consumer"));
    boolean initSender = Boolean.parseBoolean(Config.getProperty("app.init_sender"));
    int consumerConcurrentClients = Integer.parseInt(Config.getProperty("app.servicebus.concurrent_clients"));
    int senderConcurrentClients = Integer.parseInt(Config.getProperty("app.sender_concurrent_clients"));

    
  }

}