package com.cloud.studentcloudservice.feignclient;

import feign.Feign;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

/**
 * if you call address service from api gateway instead of calling it directly from this service, no need to define this load balancer
 */
@LoadBalancerClient(value = "address-service")
public class AddressServiceLoadBalancerConfig {

    @LoadBalanced
    @Bean
    public Feign.Builder feignBuilder() {

        return Feign.builder();
    }
}
