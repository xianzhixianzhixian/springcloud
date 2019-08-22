package com.feign.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "consul-consumer") //这里写需要调用的服务的application-name
public interface MemberFeign {

    //这里对应服务中的请求地址,就映射到了consul-consumer的Controller中的/consumer/getServices接口
    @RequestMapping(value = "/consumer/getServices")
    String getServices();
}
