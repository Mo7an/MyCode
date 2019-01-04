package com.javainuse;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClientException;

import com.javainuse.controllers.ConsumerControllerClient;

@SpringBootApplication
public class SpringBootConsumer {

	public static void main(String[] args) throws RestClientException, IOException, InterruptedException {
		ApplicationContext ctx = SpringApplication.run(
				SpringBootConsumer.class, args);
		
		ConsumerControllerClient consumerControllerClient=ctx.getBean(ConsumerControllerClient.class);
		System.out.println(consumerControllerClient);
		//for(int i=0;i<=50;i++) {
		while(true) {
		consumerControllerClient.getEmployee();}
		//Thread.sleep(10000/5);
		//}
	}
	
	@Bean
	public  ConsumerControllerClient  consumerControllerClient()
	{
		return  new ConsumerControllerClient();
	}
}
