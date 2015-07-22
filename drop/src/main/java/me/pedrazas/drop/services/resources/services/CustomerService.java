package me.pedrazas.drop.services.resources.services;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;

import me.pedrazas.drop.services.om.Customer;
import me.pedrazas.drop.services.utils.RandomString;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class CustomerService {

	
	public static int getCustomersCount(HazelcastInstance client){
		IMap map = client.getMap("customers");
		return map.size();
	}
	
	public static void addCustomer(HazelcastInstance client){
		ConcurrentMap<String, Customer> customers = client.getMap( "customers" );
		Customer c = CustomerService.createRandom();
    	customers.put(c.getId(), c);
	}
	
	public static Customer createRandom(){
		RandomString rs = new RandomString(300);
		Random randomGenerator = new Random();
        int age = randomGenerator.nextInt(100);
        
		return new Customer(rs.nextString(), rs.nextString(), rs.nextString(), age, rs.nextString(), new Date());		
	}
}
