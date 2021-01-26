package com.kalangga.rhsservice.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

@Configuration
public class OneSignal {

	@Value("${onesignal.app_key}")
	private String onesignalAppKey;

	@Value("${onesignal.app_id}")
	private String onesignalAppId;

	@Value("${onesignal.url}")
	private String url;

	/**
	* 
	* The function notification method is hit API thirtparty one signal
	*
	*/
	public void notification (String title , String content, String token) throws ClientProtocolException, IOException, JSONException {
		HttpPost charge = new HttpPost(url);
		charge.setHeader("Content-type", MediaType.APPLICATION_JSON_VALUE);
		charge.setHeader("Accept", MediaType.APPLICATION_JSON_VALUE);
		charge.setHeader("Authorization", "Basic " + onesignalAppKey);

		HttpClient httpClient = HttpClientBuilder.create().build();
		JSONObject json = new JSONObject();
		List<String> tokens = new ArrayList<String>();
		tokens.add(token);
		JSONObject jsonChildHeading = new JSONObject();
		jsonChildHeading.put("en", title);
		JSONObject jsonChildContent = new JSONObject();
		jsonChildContent.put("en", content);
		json.put("action", "GENERAL");
		json.put("app_id", onesignalAppId);
		json.put("headings", jsonChildHeading);
		json.put("contents", jsonChildContent);
		json.put("include_player_ids", tokens);
		StringEntity data = new StringEntity(json.toString());
		charge.setEntity(data);
		HttpResponse response = httpClient.execute(charge);
	}
}
