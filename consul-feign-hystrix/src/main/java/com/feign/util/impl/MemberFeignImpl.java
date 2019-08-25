package com.feign.util.impl;

import com.feign.util.MemberFeign;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class MemberFeignImpl implements MemberFeign {

    @Override
    public String getServices() {
        return "通过实现MemberFeign接口去处理降级";
    }
}
