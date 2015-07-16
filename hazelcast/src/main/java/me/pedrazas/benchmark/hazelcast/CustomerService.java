package me.pedrazas.benchmark.hazelcast;

import java.util.Random;
import java.util.concurrent.ConcurrentMap;

import me.pedrazas.benchmark.RandomString;

import com.hazelcast.core.HazelcastInstance;

public class CustomerService {

	Customer getCustomer( String id, HazelcastInstance hazel ) {
	    ConcurrentMap<String, Customer> customers = hazel.getMap( "customers" );
	    Customer customer = customers.get( id );
	    return customer;
	}  
	
	public static Customer createRandom(){
		RandomString rs = new RandomString(8);
		Random randomGenerator = new Random();
        int age = randomGenerator.nextInt(100);
        
		return new Customer(rs.nextString(), rs.nextString(), rs.nextString(), age);		
	}
}
