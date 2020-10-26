package com.kalangga.rhsservice.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SaveXplayerRequest {

	Long id;

	@JsonProperty("member_code")
	private String memberCode;

	@JsonProperty("source")
	private String source;

	@JsonProperty("token")
	private String token;
}
