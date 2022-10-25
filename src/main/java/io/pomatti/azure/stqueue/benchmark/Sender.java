package io.pomatti.azure.stqueue.benchmark;

import com.azure.storage.queue.QueueClient;
import com.azure.storage.queue.models.SendMessageResult;

public class Sender {

  private QueueClient queueClient;
  private String message;

  public void start() {
    var messageSize = Integer.parseInt(Config.getProperty("app.message_size_in_bytes"));
    message = "8".repeat(messageSize);
    queueClient = Utils.getStorageQueueClient();
  }

  public void send() {
    SendMessageResult response = queueClient.sendMessage(message);
  }

}
