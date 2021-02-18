package com.nagp.ucp.service.catalog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.nagp.ucp.service.catalog.client.RatingClient;
import com.nagp.ucp.service.catalog.domain.Pricing;
import com.nagp.ucp.service.catalog.domain.QuotedService;
import com.nagp.ucp.service.catalog.domain.Rating;
import com.nagp.ucp.service.catalog.domain.Service;
import com.nagp.ucp.service.catalog.repository.CatalogRepository;

@org.springframework.stereotype.Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private RatingClient ratingClient;

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
        List<Service> catalog = new ArrayList<>();
        catalogRepository.findAll().forEach(service -> catalog.add(service));
        return catalog;
    }

    @Override
    public Service fetchCatalogById(int id) {
        return getServiceFromDB(id);

    }

    @Override
    public List<Service> fetchCatalogByPincode(int pincode) {
        return catalogRepository.findByPincode(pincode);
    }

    @Override
    public QuotedService fetchServicePricing(int id) {
        QuotedService quote = new QuotedService();
        Service service = getServiceFromDB(id);
        quote.setId(service.getId());
        quote.setName(service.getName());
        quote.setPincode(service.getPincode());
        quote.setAvailable(service.isAvailable());
        quote.setDescription(service.getDescription());

        // Fetch Pricing from Pricing Service.

        String baseUrl = loadBalancerClient.choose("ucppricing").getUri().toString() + "/pricing/fetch/" + service
            .getId();
        restTemplate = new RestTemplate();
        ResponseEntity<Pricing> response = null;

        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl);
            response = restTemplate.exchange(builder.buildAndExpand().toUri(), HttpMethod.GET, null, Pricing.class);

            if (null != response.getBody()) {
                Pricing pricing = response.getBody();
                quote.setPrice(pricing.getPrice());
                quote.setDiscount(pricing.getDiscount());
                quote.setQuoteOnInspection(pricing.isOnInspection());
            }
        } catch (Exception ex) {
            // handle-exception
        }
        return quote;
    }

    @Override
    public List<Rating> fetchServiceRating(int id) {
        return ratingClient.getRatings(id);
    }

    private Service getServiceFromDB(int id) {
        Optional<Service> serviceOptional = catalogRepository.findById(id);
        Service service = null;
        if (serviceOptional.isPresent()) {
            service = serviceOptional.get();
        } else {
            // handle exception
        }
        return service;
    }

}
