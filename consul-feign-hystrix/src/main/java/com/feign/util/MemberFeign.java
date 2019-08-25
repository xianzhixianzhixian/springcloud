package com.feign.util;

import com.feign.util.impl.MemberFeignImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 这里写需要调用的服务的application-name
 * fallback表示失败的时候调用中包含降级方法的类
 */
@Qualifier("MemberFeign")
@FeignClient(value = "consul-consumer", fallback = MemberFeignImpl.class)
public interface MemberFeign {

    //这里对应服务中的请求地址,就映射到了consul-consumer的Controller中的/consumer/getServices接口
    @RequestMapping(value = "/consumer/getServices")
    String getServices();
}
