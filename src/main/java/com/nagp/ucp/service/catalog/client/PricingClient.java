package com.nagp.ucp.service.catalog.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nagp.ucp.common.responses.BaseResponse;
import com.nagp.ucp.service.catalog.domain.Pricing;

@FeignClient("${UCP_PRICING_SERVICE_URL:http://ucppricing}")
public interface PricingClient {

	@RequestMapping(method = RequestMethod.GET, value = "/pricing/{serviceId}")
	public BaseResponse<Pricing> getPricing(@PathVariable int serviceId);

}
