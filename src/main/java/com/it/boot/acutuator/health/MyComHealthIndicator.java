package com.it.boot.acutuator.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MyComHealthIndicator extends AbstractHealthIndicator {

    /*
    * 真实的检查方法
    * */
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        //mongodb   获取连接进行测试
        Map<String,Object> map = new HashMap<>();
        if (1==1){
            builder.up();
            map.put("count",1);
            map.put("ms",1000);
        }else {
//            builder.down();
            builder.status(Status.OUT_OF_SERVICE);
            map.put("err","连接超时");
            map.put("ms",3000);
        }

        builder.withDetail("code",100)
                .withDetails(map);
    }
}
