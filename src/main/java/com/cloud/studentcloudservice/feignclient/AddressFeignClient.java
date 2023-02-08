package com.cloud.studentcloudservice.feignclient;

import com.cloud.studentcloudservice.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * to call the address service from api gateway instead of calling the service directly, change the annotation to the following
 * @FeignClient(value = "api-gateway")
 *
 * and put GetMapping("address-service/api/address/getById/{id}" on getById controller method
 */
//@FeignClient(url = "${address.service.url}", value = "address-feign-client", path = "/api/address")
@FeignClient(value = "address-service", path = "/api/address")
public interface AddressFeignClient {

    @GetMapping("/getById/{id}")
    public AddressResponse getById(@PathVariable long id);
}
