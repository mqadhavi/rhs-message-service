package com.kalangga.rhsservice.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GeneralNotifRequest {

	Long id;
	
	@JsonProperty("message_to")
	private String messageTo;
	
	@JsonProperty("platform")
	private String platform;
	
	private String title;

	private String content;
	
}
