package me.pedrazas.benchmark.hazelcast;

import java.util.Random;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class BenchMap {

	private static Random randomGenerator = new Random();
	
	public static void main(String[] args) {
		
		CommandLineParser parser = new DefaultParser();
		Options options = new Options();
		options.addOption( "h", "hazelcast", false, "Hazelcast connection" );
		options.addOption( "a", "objects-added", false, "Number of objects added" );
		String hazelcastUrl = "localhost:5701";
		int numObjects = 1000;
		try {
		    // parse the command line arguments
		    CommandLine line = parser.parse( options, args );

		    if( line.hasOption( "hazelcast" ) ) {
		        hazelcastUrl = line.getOptionValue("hazelcast");
		    }
		    if( line.hasOption( "objects-added" ) ) {
		    	numObjects = Integer.parseInt(line.getOptionValue("objects-added"));
		    }
		}
		catch( ParseException exp ) {
		    System.out.println( "Unexpected exception:" + exp.getMessage() );
		}
		
		ClientConfig clientConfig = new ClientConfig();
        clientConfig.addAddress(hazelcastUrl);
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        
        for (int i = 0; i < numObjects; i++) {
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
