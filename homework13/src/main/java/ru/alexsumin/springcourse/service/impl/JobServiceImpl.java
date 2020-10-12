package ru.alexsumin.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.stereotype.Service;
import ru.alexsumin.springcourse.service.JobService;

@Slf4j
@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobServiceImpl implements JobService {
    final JobOperator jobOperator;
    @Getter
    Long executionId;

    @SneakyThrows
    @Override
    public void runJob() {
        executionId = jobOperator.start("migrateJpaToMongoJob", "");
        log.info("Started with id: {}", jobOperator.getSummary(executionId));
    }

    @SneakyThrows
    @Override
    public void restartJob() {
        if (executionId == null) throw new IllegalArgumentException("Job haven't been started yet");
        jobOperator.restart(executionId);
    }

    @SneakyThrows
    @Override
    public String showJobInfo() {
        return jobOperator.getSummary(executionId);
    }
}
