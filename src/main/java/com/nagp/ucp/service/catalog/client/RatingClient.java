package com.nagp.ucp.service.catalog.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nagp.ucp.service.catalog.domain.Rating;

@FeignClient("${NGM_RATING_SERVICE_URL:http://ucprating}")
public interface RatingClient {

	@RequestMapping(method = RequestMethod.GET, value = "/rating/{serviceId}")
	public List<Rating> getRatings(@PathVariable int serviceId);

}
