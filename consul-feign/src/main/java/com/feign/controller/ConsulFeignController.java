package com.feign.controller;

import com.feign.util.MemberFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/feign")
public class ConsulFeignController {

    @Autowired
    private MemberFeign memberFeign;

    @RequestMapping(value = "/getServices")
    public String getServices() {
        return memberFeign.getServices();
    }
}
