package com.ly.cloud.remote.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "ly-zhxg-xtgl-svc") 
public interface XtglServiceFeignClient {
    
//    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
//    public WebResponse<UserDTO> get(@PathVariable("userId") String userId);

}
  