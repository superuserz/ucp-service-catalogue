package com.nagp.ucp.service.catalog.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagp.ucp.common.constant.QueueConstants;
import com.nagp.ucp.common.exception.UCPException;
import com.nagp.ucp.common.request.AddRatingRequest;
import com.nagp.ucp.common.responses.BaseResponse;
import com.nagp.ucp.service.catalog.domain.QuotedService;
import com.nagp.ucp.service.catalog.domain.Rating;
import com.nagp.ucp.service.catalog.domain.Service;
import com.nagp.ucp.service.catalog.service.CatalogService;

import io.swagger.annotations.ApiOperation;

/*
 * This Controller is responsible for returning the list of services available to the customers based on the location.
 * For demonstration purpose consumer pin codes will be used for their location discovery
 */

@RestController
@RequestMapping("/catalog")
public class CatalogController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogController.class);

	@Autowired
	private RabbitTemplate publisher;

	@Autowired
	private CatalogService catalogService;

	@GetMapping(value = "/fetch")
	@ApiOperation(value = "Gets the Complete List of Catalog/Services Available to Users")
	public BaseResponse<List<Service>> getCatalog() throws UCPException {
		return new BaseResponse<>(catalogService.fetchCatalog());
	}

	@GetMapping(value = "/fetch/{id}")
	@ApiOperation(value = "Gets Details of a Service by its Service ID")
	public BaseResponse<Service> getCatalogById(@PathVariable int id) throws UCPException {
		return new BaseResponse<>(catalogService.fetchCatalogById(id));
	}

	@GetMapping(value = "/fetch/pincode/{pincode}")
	@ApiOperation(value = "Gets the Complete List of Catalog/Services Available to Users on the Basic of their Pincode")
	public BaseResponse<List<Service>> getCatalogByPincode(@PathVariable int pincode) throws UCPException {
		return new BaseResponse<>(catalogService.fetchCatalogByPincode(pincode));
	}

	@GetMapping(value = "/fetch/{id}/pricing")
	@ApiOperation(value = "Gets the Pricing info of a Service on the Basis of its service ID")
	public BaseResponse<QuotedService> getServicePricing(@PathVariable int id) throws UCPException {
		return new BaseResponse<>(catalogService.fetchServicePricing(id));
	}

	@GetMapping(value = "/fetch/{id}/rating")
	@ApiOperation(value = "Gets the Ratings List for a Service on the Basic of Service ID")
	public BaseResponse<List<Rating>> getServiceRating(@PathVariable int id) throws UCPException {
		return new BaseResponse<>(catalogService.fetchServiceRating(id));
	}

	@PostMapping(value = "/rating")
	@ApiOperation(value = "Publish a new Rating to Rating Service")
	public BaseResponse<String> postServiceRating(@RequestBody AddRatingRequest request) throws UCPException {
		publisher.convertAndSend(QueueConstants.EXCHANGE, QueueConstants.ROUTING, request);
		return new BaseResponse<>("Your Feedback Has Been Submitted");

	}

}
