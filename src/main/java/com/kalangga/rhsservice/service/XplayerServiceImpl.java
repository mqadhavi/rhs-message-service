package com.kalangga.rhsservice.service;

import java.time.Instant;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kalangga.rhsservice.base.BaseResponse;
import com.kalangga.rhsservice.constant.Default;
import com.kalangga.rhsservice.entity.AgentXplayer;
import com.kalangga.rhsservice.entity.Agents;
import com.kalangga.rhsservice.entity.Couriers;
import com.kalangga.rhsservice.entity.CouriersXplayer;
import com.kalangga.rhsservice.entity.Customers;
import com.kalangga.rhsservice.entity.CustomersXplayer;
import com.kalangga.rhsservice.entity.ShipmentNotifications;
import com.kalangga.rhsservice.entity.repository.AgentRepository;
import com.kalangga.rhsservice.entity.repository.AgentXplayerRepository;
import com.kalangga.rhsservice.entity.repository.CourierRepository;
import com.kalangga.rhsservice.entity.repository.CourierXplayerRepository;
import com.kalangga.rhsservice.entity.repository.CustomerRepository;
import com.kalangga.rhsservice.entity.repository.CustomerXplayerRepository;
import com.kalangga.rhsservice.entity.repository.ShipmentNotificationRepository;
import com.kalangga.rhsservice.enums.SourceType;
import com.kalangga.rhsservice.payload.GeneralNotifRequest;
import com.kalangga.rhsservice.payload.SaveXplayerRequest;
import com.kalangga.rhsservice.payload.StatusNotifRequest;
import com.kalangga.rhsservice.util.OneSignal;

@Service
public class XplayerServiceImpl implements XplayerService {

	@Autowired
	CustomerXplayerRepository custXplayerRepository;

	@Autowired
	CustomerRepository custRepository;

	@Autowired
	AgentXplayerRepository agentXplayerRepository;

	@Autowired
	AgentRepository agentRepository;

	@Autowired
	CourierXplayerRepository courierXplayerRepository;

	@Autowired
	CourierRepository courierRepository;

	@Autowired
	ShipmentNotificationRepository shipmentNotificationRepository;
	
	@Autowired
	private OneSignal oneSignal;
	
	/**
	* 
	* The function saveToken implements an application that
	* save data token for agent, customer and courier/sprinter
	*
	*/
	@Override
	public BaseResponse saveToken(SaveXplayerRequest request) {
		BaseResponse response = new BaseResponse<>();
		try {
			if (request.getMemberCode() == "" || request.getSource() == "" || request.getToken() == "") {
				response.sendBadRequest(null, "Source, member, token is mandatory, pls check");
				return response;
			}

			if (request.getSource().equalsIgnoreCase(SourceType.USER_APP.toString())) {
				Optional<Customers> cust = custRepository.findByMemberCode(request.getMemberCode());
				if (!cust.isPresent()) {
					response.sendNotFound(null, "Member code not found");
					return response;
				}
				checkingTokenCustomers(cust.get(), request);

			} else if (request.getSource().equalsIgnoreCase(SourceType.COURIER_APP.toString())) {
				Optional<Couriers> courier = courierRepository.findByCode(request.getMemberCode());
				if (!courier.isPresent()) {
					response.sendNotFound(null, "Member code not found");
					return response;
				}
				checkingTokenCourier(courier.get(), request);

			} else if (request.getSource().equalsIgnoreCase(SourceType.AGENT_APP.toString())) {
				Optional<Agents> agen = agentRepository.findByAgentCode(request.getMemberCode());
				if (!agen.isPresent()) {
					response.sendNotFound(null, "Member code not found");
					return response;
				}
				checkingTokenAgent(agen.get(), request);
			}
			response.sendSuccess(null, "Success");
			return response;

		} catch (Exception e) {
			response.sendBadRequest(null, "Failed insert token xplayer");
			return response;
		}
	}
	

	public void checkingTokenCustomers(Customers cust, SaveXplayerRequest request) {
		Optional<CustomersXplayer> custXplayer = custXplayerRepository.findByCustomer(cust);

		if (custXplayer.isPresent()) {
			custXplayer.get().setToken(request.getToken());
			custXplayer.get().setUpdatedAt(Instant.now());
			custXplayerRepository.save(custXplayer.get());
		} else {
			CustomersXplayer newXplayer = new CustomersXplayer();
			newXplayer.setCustomer(cust);
			newXplayer.setToken(request.getToken());
			newXplayer.setCreatedAt(Instant.now());
			custXplayerRepository.save(newXplayer);
		}
	}

	public void checkingTokenCourier(Couriers courier, SaveXplayerRequest request) {
		Optional<CouriersXplayer> courierXplayer = courierXplayerRepository.findByCourier(courier);
		if (courierXplayer.isPresent()) {
			courierXplayer.get().setToken(request.getToken());
			courierXplayer.get().setUpdatedAt(Instant.now());
			courierXplayerRepository.save(courierXplayer.get());
		} else {
			CouriersXplayer newCourierXplayer = new CouriersXplayer();
			newCourierXplayer.setCourier(courier);
			newCourierXplayer.setToken(request.getToken());
			newCourierXplayer.setCreatedAt(Instant.now());
			courierXplayerRepository.save(newCourierXplayer);
		}
	}

	public void checkingTokenAgent(Agents agent, SaveXplayerRequest request) {
		Optional<AgentXplayer> agentXplayer = agentXplayerRepository.findByAgent(agent);
		if (agentXplayer.isPresent()) {
			agentXplayer.get().setToken(request.getToken());
			agentXplayer.get().setUpdatedAt(Instant.now());
			agentXplayerRepository.save(agentXplayer.get());
		} else {
			AgentXplayer newAgentXplayer = new AgentXplayer();
			newAgentXplayer.setAgent(agent);
			newAgentXplayer.setToken(request.getToken());
			newAgentXplayer.setCreatedAt(Instant.now());
			agentXplayerRepository.save(newAgentXplayer);
		}
	}
	
	/**
	* 
	* The function generalNotification implements an application that
	* for general notification in customer, agent and courier/sprinter
	*
	*/
	@Override
	public BaseResponse generalNotification(GeneralNotifRequest request) {
		BaseResponse response = new BaseResponse();
	
		try {
			if (request.getPlatform().equalsIgnoreCase(SourceType.USER_APP.toString())) {
				Optional<Customers> cust = custRepository.findByMemberCode(request.getMessageTo());
				if (!cust.isPresent()) {
					response.sendNotFound(null, "Member code not found");
					return response;
				}
				Optional<CustomersXplayer> custXplayer = custXplayerRepository.findByCustomer(cust.get());
				if (custXplayer.isPresent()) {
					oneSignal.notification(request.getTitle(), request.getContent(), custXplayer.get().getToken());
				}
			} else if (request.getPlatform().equalsIgnoreCase(SourceType.AGENT_APP.toString())) {
				Optional<Agents> agen = agentRepository.findByAgentCode(request.getMessageTo());
				if (!agen.isPresent()) {
					response.sendNotFound(null, "Member code not found");
					return response;
				}
				Optional<AgentXplayer> agentXplayer = agentXplayerRepository.findByAgent(agen.get());
				if (agentXplayer.isPresent()) {
					oneSignal.notification(request.getTitle(), request.getContent(), agentXplayer.get().getToken());
				}

			} else if (request.getPlatform().equalsIgnoreCase(SourceType.COURIER_APP.toString())) {
				Optional<Couriers> courier = courierRepository.findByCode(request.getMessageTo());
				if (!courier.isPresent()) {
					response.sendNotFound(null, "Member code not found");
					return response;
				}
				Optional<CouriersXplayer> courierXplayer = courierXplayerRepository.findByCourier(courier.get());
				if (courierXplayer.isPresent()) {
					oneSignal.notification(request.getTitle(), request.getContent(), courierXplayer.get().getToken());
				}
			}
			response.sendSuccess(null, "Success");
			return response;

		} catch (Exception e) {
			response.sendBadRequest(null, "Failed send general notif");
			return response;
		}
	}
	
	/**
	* 
	* The function statusNotification implements an application that
	* for status notification when the status changed and every status will be save in notif DB
	*
	*/
	@Override
	public BaseResponse statusNotification(StatusNotifRequest request) {
		BaseResponse response = new BaseResponse();
		
		try {
			if (request.getMemberCode().contains(Default.PREFIX_AGN) || request.getMemberCode().contains(Default.PREFIX_A)) {
				Optional<Agents> agent = agentRepository.findByAgentCode(request.getMemberCode());
				if (!agent.isPresent()) {
					response.sendNotFound(null, "Agent not found");
					return response;
				}
				Optional<AgentXplayer> agentXplayer = agentXplayerRepository.findByAgent(agent.get());
				if (agentXplayer.isPresent()) {
					oneSignal.notification(request.getTitle(), request.getContent(), agentXplayer.get().getToken());
					saveShipmentNotif(request);
				}

			} else if (request.getMemberCode().contains(Default.PREFIX_CUS) || request.getMemberCode().contains(Default.PREFIX_C)) {
				Optional<Customers> cust = custRepository.findByMemberCode(request.getMemberCode());
				if (!cust.isPresent()) {
					response.sendNotFound(null, "Member not found");
					return response;
				}
				Optional<CustomersXplayer> custXplayer = custXplayerRepository.findByCustomer(cust.get());
				if (custXplayer.isPresent()) {
					oneSignal.notification(request.getTitle(), request.getContent(), custXplayer.get().getToken());
					saveShipmentNotif(request);
				}
			}

			response.sendSuccess(null, "Success");
			return response;

		} catch (Exception e) {
			response.sendBadRequest(null, "Failed send status notif");
			return response;
		}
	}

	public void saveShipmentNotif(StatusNotifRequest request) {
		ShipmentNotifications shipNotif = new ShipmentNotifications();
		shipNotif.setMemberCode(request.getMemberCode());
		shipNotif.setShipmentCode(request.getShipmentCode());
		shipNotif.setStatus(request.getStatus());
		shipNotif.setMessage(request.getContent());
		shipmentNotificationRepository.save(shipNotif);
	}
}