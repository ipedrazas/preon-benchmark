package me.pedrazas.drop.services;

import io.dropwizard.Configuration;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;


public class WrapConfiguration extends Configuration {
	
     @NotEmpty
     private String hazelcastUrl = "hazelcast:5701";
     
     @JsonProperty
     public String getHazelcasrUrl(){
    	 return hazelcastUrl;
     }
     
     @JsonProperty
     public void setHazelcastUrl(String url){
    	 this.hazelcastUrl = url;
     }

	@NotEmpty
    private String defaultName = "Wrap";
	
	@JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }
}
