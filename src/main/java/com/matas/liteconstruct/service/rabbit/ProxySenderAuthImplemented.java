package com.matas.liteconstruct.service.rabbit;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProxySenderAuthImplemented implements ProxySender {

  @Autowired
  private RabbitTemplate template;

  @Autowired
  private FanoutExchange fanout;

  @Override
  public void producer(String... args) {
    StringBuilder builder = new StringBuilder("Hello");
    // if (dots.getAndIncrement() == 3) {
    // dots.set(1);
    // }
    // for (int i = 0; i < dots.get(); i++) {
    // builder.append('.');
    // }
    // builder.append(count.incrementAndGet());
    String message = builder.toString();
    template.convertAndSend(fanout.getName(), "", message);
    log.info(" [x] Sent '{}'", message);
  }

}
