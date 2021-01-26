package com.kalangga.rhsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kalangga.rhsservice.base.BaseResponse;
import com.kalangga.rhsservice.payload.GeneralNotifRequest;
import com.kalangga.rhsservice.payload.SaveXplayerRequest;
import com.kalangga.rhsservice.payload.StatusNotifRequest;
import com.kalangga.rhsservice.service.XplayerService;

@RestController
@RequestMapping(value = "api/v1/x-player")
public class XplayerController {

	@Autowired
	XplayerService xplayerService;
	
	/**
	* 
	* The url api for save token xplayer
	*
	*/
	@PostMapping("/token")
	public ResponseEntity<BaseResponse> createToken(@RequestBody SaveXplayerRequest request) {
		BaseResponse response = xplayerService.saveToken(request);
		return ResponseEntity.status(response.getHttpCode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	/**
	* 
	* The url api for general notification
	*
	*/
	@PostMapping("/general-notification")
	public ResponseEntity<BaseResponse> generalNotification(@RequestBody GeneralNotifRequest request) {
		BaseResponse response = xplayerService.generalNotification(request);
		return ResponseEntity.status(response.getHttpCode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	/**
	* 
	* The url api for status notification
	*
	*/
	@PostMapping("/status-notification")
	public ResponseEntity<BaseResponse> statusNotification(@RequestBody StatusNotifRequest request) {
		BaseResponse response = xplayerService.statusNotification(request);
		return ResponseEntity.status(response.getHttpCode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}

}
