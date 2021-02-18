package com.nagp.ucp.service.catalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nagp.ucp.service.catalog.domain.Service;

public interface CatalogRepository extends JpaRepository<Service, Integer> {

    @Query(value = "select u from Service u where u.pincode = ?1")
    public List<Service> findByPincode(int pincode);

}
