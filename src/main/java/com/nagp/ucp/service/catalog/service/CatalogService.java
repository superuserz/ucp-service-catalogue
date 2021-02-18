package com.nagp.ucp.service.catalog.service;

import java.util.List;

import com.nagp.ucp.service.catalog.domain.QuotedService;
import com.nagp.ucp.service.catalog.domain.Rating;
import com.nagp.ucp.service.catalog.domain.Service;

public interface CatalogService {

    public List<Service> fetchCatalog();

    public Service fetchCatalogById(int id);

    public QuotedService fetchServicePricing(int id);

    public List<Service> fetchCatalogByPincode(int pincode);

    public List<Rating> fetchServiceRating(int id);

}
