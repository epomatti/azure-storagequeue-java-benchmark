package io.pomatti.azure.stqueue.benchmark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

  private static Logger logger = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) throws Exception {
    Config.load();
    logger.info("Loaded configuration.");

    boolean initSender = Boolean.parseBoolean(Config.getProperty("app.init_sender"));
    int senderConcurrentClients = Integer.parseInt(Config.getProperty("app.concurrent_sender_clients"));

    if (initSender) {
      for (int i = 0; i < senderConcurrentClients; i++) {
        new SenderThread().start();
      }
    }

  }

}
