package com.matas.liteconstruct.service.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProxyReceiverAuthImplemented implements ProxyReceiver {

  @RabbitListener(queues = "#{authQueueServer.name}")
  public void receive2(String in) throws InterruptedException {
    consumer(in);
  }

  @Override
  public void consumer(String... args) {
    log.info("receive auth info: {}", args[0]);
  }

}
