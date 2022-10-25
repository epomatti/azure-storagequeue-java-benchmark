package io.pomatti.azure.stqueue.benchmark;

public class SenderThread extends Thread {

  @Override
  public void run() {
    var sender = new Sender();
    sender.start();
    sender.send();
  }

}
