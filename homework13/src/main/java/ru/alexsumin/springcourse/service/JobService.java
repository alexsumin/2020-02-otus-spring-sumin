package ru.alexsumin.springcourse.service;

public interface JobService {
    void runJob();
    Long getExecutionId();
    void restartJob();
    String showJobInfo();
}
