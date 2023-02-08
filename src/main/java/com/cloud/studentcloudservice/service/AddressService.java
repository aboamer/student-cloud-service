package com.cloud.studentcloudservice.service;

import com.cloud.studentcloudservice.entity.Student;
import com.cloud.studentcloudservice.feignclient.AddressFeignClient;
import com.cloud.studentcloudservice.response.AddressResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * because CircuitBreaker uses AOP, it should be called from another class because this method is proxied
 */
@Service
public class AddressService {

    Logger logger = LoggerFactory.getLogger(AddressService.class);

    int count = 1;

    @Autowired
    AddressFeignClient addressFeignClient;

    @CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById")
    public AddressResponse getAddressById(Student student) {

        logger.info("count = " + count);
        count++;

        return addressFeignClient.getById(student.getAddressId());
    }

    private AddressResponse fallbackGetAddressById(Throwable th) {

        logger.error("Error = " + th);
        return new AddressResponse();
    }
}
