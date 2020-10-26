package com.kalangga.rhsservice.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StatusNotifRequest {
	
	@JsonProperty("member_code")
	private String memberCode;

	@JsonProperty("shipment_code")
	private String shipmentCode;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("content")
	private String content;
}
