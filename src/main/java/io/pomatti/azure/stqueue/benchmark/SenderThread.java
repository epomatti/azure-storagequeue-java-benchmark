package io.pomatti.azure.stqueue.benchmark;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SenderThread extends Thread {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public void run() {
    int concurrency = Integer.parseInt(Config.getProperty("app.concurrent_processes_per_client"));
    int qty = Integer.parseInt(Config.getProperty("app.message_quantity_to_send_per_client"));

    Set<Integer> dataset = getLargeDataset(qty);

    var sender = new Sender();
    sender.start();

    ForkJoinPool pool = new ForkJoinPool(concurrency);

    Instant starts = Instant.now();
    try {
      pool.submit(() -> dataset.stream().parallel().forEach(i -> {
        sender.send();
      })).get();
      Instant ends = Instant.now();

      long durationInSeconds = Duration.between(starts, ends).toMillis() / 1000;

      logger.info(String.format("Total messages sent: %s", qty));
      logger.info(String.format("Duration: %s seconds", durationInSeconds));
      if (durationInSeconds > 0) {
        logger.info(String.format("Throughput: %s messages/sec", qty / durationInSeconds));
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    sender.send();
  }

  private Set<Integer> getLargeDataset(int qty) {
    Set<Integer> counter = new HashSet<>();
    for (int i = 0; i < qty; i++) {
      counter.add(i);
    }
    return counter;
  }

}
