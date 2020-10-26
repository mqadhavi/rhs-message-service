package com.kalangga.rhsservice.service;

import com.kalangga.rhsservice.base.BaseResponse;
import com.kalangga.rhsservice.payload.SaveXplayerRequest;

public interface XplayerService {
	
	BaseResponse saveToken(SaveXplayerRequest request); 
		
	

}
