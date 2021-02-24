package com.nagp.ucp.service.catalog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.nagp.ucp.common.exception.UCPException;
import com.nagp.ucp.common.responses.BaseResponse;
import com.nagp.ucp.service.catalog.client.PricingClient;
import com.nagp.ucp.service.catalog.client.RatingClient;
import com.nagp.ucp.service.catalog.domain.Pricing;
import com.nagp.ucp.service.catalog.domain.QuotedService;
import com.nagp.ucp.service.catalog.domain.Rating;
import com.nagp.ucp.service.catalog.domain.Service;
import com.nagp.ucp.service.catalog.repository.CatalogRepository;

@org.springframework.stereotype.Service
public class CatalogServiceImpl implements CatalogService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogServiceImpl.class);

	@Autowired
	private RatingClient ratingClient;

	@Autowired
	private PricingClient pricingClient;

	@Autowired
	private CatalogRepository catalogRepository;

	@Value("${server.port}")
	private int port;

	@Autowired
	LoadBalancerClient loadBalancerClient;

	@Resource
	private RestTemplate restTemplate;

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Override
	public List<Service> fetchCatalog() {

		LOGGER.info("Fetching Catalog List");
		List<Service> catalog = new ArrayList<>();
		catalogRepository.findAll().forEach(service -> catalog.add(service));
		if (catalog.isEmpty()) {
			throw new UCPException("ucp.service.catalog.001", "Catalog is Empty");
		}
		return catalog;
	}

	@Override
	public Service fetchCatalogById(int id) throws UCPException {
		return getServiceFromDB(id);

	}

	@Override
	public List<Service> fetchCatalogByPincode(int pincode) throws UCPException {
		List<Service> service = catalogRepository.findByPincode(pincode);
		if (null == service || service.isEmpty()) {
			throw new UCPException("ucp.service.catalog.002", "No Service returned for Pincode : " + pincode);
		}
		return service;
	}

	@Override
	public QuotedService fetchServicePricing(int id) throws UCPException {
		QuotedService quote = new QuotedService();
		Service service = getServiceFromDB(id);
		quote.setId(service.getId());
		quote.setName(service.getName());
		quote.setPincode(service.getPincode());
		quote.setAvailable(service.isAvailable());
		quote.setDescription(service.getDescription());

		// Fetch Pricing from Pricing Service.
		BaseResponse<Pricing> response = null;
		Pricing pricing = null;
		response = pricingClient.getPricing(id);
		if (response != null) {
			pricing = response.getData();
		}
		if (null != pricing) {
			quote.setPrice(pricing.getPrice());
			quote.setDiscount(pricing.getDiscount());
			quote.setQuoteOnInspection(pricing.isOnInspection());
		} else {
			throw new UCPException("ucp.service.catalog.003", "Pricing for service ID : " + service.getId()
					+ " Not Available in system. Try Again After adding the Price in DB");
		}

		return quote;
	}

	@Override
	public List<Rating> fetchServiceRating(int id) throws UCPException {
		BaseResponse<List<Rating>> response = null;
		List<Rating> ratings = new ArrayList<>();
		response = ratingClient.getRatings(id);
		if (response != null) {
			ratings = response.getData();
		}
		if (null == ratings || ratings.isEmpty()) {
			throw new UCPException("ucp.service.catalog.006", "No Ratings Available for the Service Id : " + id);
		}
		return ratings;
	}

	private Service getServiceFromDB(int id) {
		Optional<Service> serviceOptional = catalogRepository.findById(id);
		Service service = null;
		if (serviceOptional.isPresent()) {
			service = serviceOptional.get();
		} else {
			throw new UCPException("ucp.service.catalog.002", "Service ID : " + id + " Not found");
		}
		return service;
	}

	/*
	 * String baseUrl = loadBalancerClient.choose("ucppricing").getUri().toString()
	 * + "/pricing/fetch/" + service.getId(); restTemplate = new RestTemplate();
	 * ResponseEntity<Pricing> response = null;
	 *
	 * try { UriComponentsBuilder builder =
	 * UriComponentsBuilder.fromUriString(baseUrl); response =
	 * restTemplate.exchange(builder.buildAndExpand().toUri(), HttpMethod.GET, null,
	 * Pricing.class);
	 *
	 * if (null != response.getBody()) { Pricing pricing = response.getBody();
	 * quote.setPrice(pricing.getPrice()); quote.setDiscount(pricing.getDiscount());
	 * quote.setQuoteOnInspection(pricing.isOnInspection()); } else { throw new
	 * UCPException("ucp.service.catalog.003", "Pricing for service ID : " +
	 * service.getId() +
	 * " Not Available in system. Try Again After adding the Price in DB"); } }
	 * catch (Exception ex) { throw new UCPException("ucp.service.catalog.004",
	 * "Unable to fetch Pricing for service ID : " + service.getId()); }
	 */
}
