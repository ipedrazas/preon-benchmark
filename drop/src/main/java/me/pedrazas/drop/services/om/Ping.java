package me.pedrazas.drop.services.om;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ping {
	private long id;

    @Length(max = 3)
    private String response;

	public Ping() {
		super();
	}

	public Ping(long id, String response) {
		super();
		this.id = id;
		this.response = response;
	}
	
	@JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getResponse() {
        return response;
    }
	
}
