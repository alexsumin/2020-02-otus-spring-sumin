package ru.alexsumin.springcourse.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    private static final LocalTime OPENING_TIME = LocalTime.of(20, 0);
    private static final LocalTime CLOSING_TIME = LocalTime.of(10, 0);

    private static final Health UP_STATUS = Health.status(Status.UP)
            .withDetail("message", "Common, we are open")
            .build();
    private static final Health DOWN_STATUS = Health.status(Status.DOWN)
            .withDetail("message", "Sorry, we don't work")
            .build();

    @Override
    public Health health() {
        var now = LocalTime.now();
        if (now.isAfter(OPENING_TIME) && now.isBefore(CLOSING_TIME)) {
            return UP_STATUS;
        }
        return DOWN_STATUS;
    }
}
