package com.kalangga.rhsservice.service;

import com.kalangga.rhsservice.base.BaseResponse;
import com.kalangga.rhsservice.payload.GeneralNotifRequest;
import com.kalangga.rhsservice.payload.SaveXplayerRequest;
import com.kalangga.rhsservice.payload.StatusNotifRequest;

public interface XplayerService {
	
	BaseResponse saveToken(SaveXplayerRequest request); 
	
	BaseResponse generalNotification (GeneralNotifRequest request);
		
	BaseResponse statusNotification (StatusNotifRequest request);

}
