package com.javainuse.controllers;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Controller
public class ConsumerControllerClient {

	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	Environment environment;
	
//	@Autowired
//	RestTemplate rest;

	public void getEmployee() throws RestClientException, IOException {

		List<ServiceInstance> instances = discoveryClient.getInstances("EMPLOYEE-PRODUCER");
		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();



		baseUrl = baseUrl + "/producer/employee";
		System.out.println("########################################### baseUrl " + baseUrl);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		System.out.println(response.getBody());
	}

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
	
	 /* @RequestMapping("/hi")
	public void getProducer() throws RestClientException, IOException {
		  ResponseEntity<String> response = null;
		  String baseUrl = serviceInstance.getUri().toString();
		  baseUrl = baseUrl + "/producer/employee";
		  response = rest.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
		  System.out.println("************************************************** "+ response);
	}*/
}