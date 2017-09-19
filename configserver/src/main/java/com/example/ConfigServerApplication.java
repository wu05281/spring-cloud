package com.example;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;

import javax.jms.Queue;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

	@Bean
	public Queue queue() {
		return new ActiveMQQueue("sample.queue");
	}


	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}
