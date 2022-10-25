package io.pomatti.azure.stqueue.benchmark;

import com.azure.storage.queue.QueueClient;
import com.azure.storage.queue.QueueClientBuilder;

public class Utils {

  public static QueueClient getStorageQueueClient() {
    var connectionString = Config.getProperty("app.storage.connectionstring");
    var queue = Config.getProperty("app.storage.queue_name");

    return new QueueClientBuilder()
        .connectionString(connectionString)
        .queueName(queue)
        .buildClient();
  }

}
