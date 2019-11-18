package com.matas.liteconstruct.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.matas.liteconstruct.service.rabbit.ProxyReceiver;
import com.matas.liteconstruct.service.rabbit.ProxyReceiverAuthImplemented;
import com.matas.liteconstruct.service.rabbit.ProxySender;
import com.matas.liteconstruct.service.rabbit.ProxySenderAuthImplemented;

@Configuration
public class RabbitMqConfig {

  @Bean
  public FanoutExchange fanout() {
    return new FanoutExchange("proxy.fanout");
  }

  final static String authQueueProxy = "auth-queue-proxy";

  final static String authQueueServer = "auth-queue-server";

  @Autowired
  RabbitTemplate rabbitTemplate;

  @Bean
  Queue authQueueProxy() {
    return new Queue(authQueueProxy, false);
  }

  @Bean
  Queue authQueueServer() {
    return new Queue(authQueueServer, false);
  }

  @Bean
  public Binding bindingQueueAuthServer(FanoutExchange fanout, Queue authQueueServer) {
    return BindingBuilder.bind(authQueueServer).to(fanout);
  }

  @Bean
  public ProxySender authSender() {
    return new ProxySenderAuthImplemented();
  }

  @Bean
  public ProxyReceiver authReceiver() {
    return new ProxyReceiverAuthImplemented();
  }
}
