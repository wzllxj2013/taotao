package com.itszt.eureka.listener;

import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

/**
 * Created by Mr.Yangxiufeng on 2017/12/9.
 * Time:13:45
 * ProjectName:Mirco-Service-Skeleton
 */
@Configuration
//@Slf4j
public class InstanceCancelListener implements ApplicationListener<EurekaInstanceCanceledEvent> {
    @Override
    public void onApplicationEvent(@NonNull EurekaInstanceCanceledEvent event) {
        System.out.println("服务:{"+event.getAppName()+"}挂了");
    }
}
