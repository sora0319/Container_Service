package org.msa.item.healthCheck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/probe")
public class HealthCheckController {
    private final Logger logger = LoggerFactory.getLogger(HealthCheckController.class);

    @GetMapping("/startup")
    public String startupCheck() {
        logger.info("Starting probe check");
        return "Good to go";
    }

    @GetMapping("/ready")
    public String readinessCheck() {
        logger.info("Readiness probe check");
        return "Ready";
    }

    @GetMapping("/live")
    public String livenessCheck() {
        logger.info("liveness probe check");
        return "OK";
    }
}
