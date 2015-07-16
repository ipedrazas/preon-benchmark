package me.pedrazas.benchmark.hazelcast;

import java.util.Random;
import java.util.concurrent.ConcurrentMap;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class BenchMap {

	private static Random randomGenerator = new Random();
	
	public static void main(String[] args) {
		ClientConfig clientConfig = new ClientConfig();
        clientConfig.addAddress("localhost:5701");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        int top = randomGenerator.nextInt(1000);
        for (int i = 0; i < top; i++) {
        	write(client);        
            read(client); 
		}
               
        client.shutdown();
	}
	
	private static void read(HazelcastInstance client){
		IMap map = client.getMap("customers");
        System.out.println("Map Size:" + map.size());
	}
	
	private static void write(HazelcastInstance client){
        int top = randomGenerator.nextInt(500);
        ConcurrentMap<String, Customer> customers = client.getMap( "customers" );        
        for (int i = 0; i < top; i++) {
        	Customer c = CustomerService.createRandom();
        	customers.put(c.getId(), c);
		}
		
	}
	
	

}
