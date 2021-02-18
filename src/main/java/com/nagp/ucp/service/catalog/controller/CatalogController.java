package com.nagp.ucp.service.catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    CatalogService catalogService;

    @GetMapping(value = "/fetch")
    @ApiOperation(value = "Gets the Complete List of Catalog/Services Available to Users")
    public List<Service> getCatalog() {
        return catalogService.fetchCatalog();
    }

    @GetMapping(value = "/fetch/{id}")
    @ApiOperation(value = "Gets Details of a Service by its Service ID")
    public Service getCatalogById(@PathVariable int id) {
        return catalogService.fetchCatalogById(id);
    }

    @GetMapping(value = "/fetch/pincode/{pincode}")
    @ApiOperation(value = "Gets the Complete List of Catalog/Services Available to Users on the Basic of their Pincode")
    public List<Service> getCatalogByPincode(@PathVariable int pincode) {
        return catalogService.fetchCatalogByPincode(pincode);
    }

    @GetMapping(value = "/fetch/{id}/pricing")
    @ApiOperation(value = "Gets the Pricing info of a Service on the Basis of its service ID")
    public QuotedService getServicePricing(@PathVariable int id) {
        return catalogService.fetchServicePricing(id);
    }

    @GetMapping(value = "/fetch/{id}/rating")
    @ApiOperation(value = "Gets the Ratings List for a Service on the Basic of Service ID")
    public List<Rating> getServiceRating(@PathVariable int id) {
        return catalogService.fetchServiceRating(id);
    }

}
