package com.example.factorialapp.healthcheck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/probe")
public class HealthcheckController {
    
    // K8S 가 호출하는 것. 사용자가 호출하는 API 가 아님
    // 컨테이너를 통해 curl 을 이용해 통신이 되는지 확인해야함

    private final Logger logger = LoggerFactory.getLogger(HealthcheckController.class);

    // K8S 의 startupProbe 가 호출하는 endPoint
    // READY 가 0 에서 1 로 바뀌는 순간

    @GetMapping("/startup")
    public String startupCheck() {
               
        // K8S 에서 중요하게 생각하는 것을 return 되는 것이 아니라 200 ok 로 반환되는 것이 더 중요함

        logger.info("Starting probe check");

        return "Good to go";
    }

    // K8S 의 readinessProbe 가 호출하는 endPoint

    @GetMapping("/ready")
    public String readinessCheck() {
        logger.info("Readiness probe check");
        return "Ready";
    }

    // K8S 의 livenessProbe 가 호출하는 endPoint

    @GetMapping("/live")
    public String livenessCheck() {
        logger.info("Liveness probe check");
        return "OK";
    }

}
