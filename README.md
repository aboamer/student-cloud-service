# student-cloud-service

#### notes
- Resttemplate is depricated in spring 5. Use WebClient instead.
- openfeign in spring cloud makes calling other apis declarative. by **FeignClient**, containing abstract method signature that is being called.
- For client load balancing, you create a config class annotated by **LoadBalancerClient** with value = service name. Then define a bean of type **Feign.Builder** annotated by LoadBalanced.
- So, your service is intelligent to not load on one service instance while there are others that could be used.
