package ru.alexsumin.springcourse.ui;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.alexsumin.springcourse.service.JobService;

@RequiredArgsConstructor
@ShellComponent
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShellService {
    JobService jobService;

    @SuppressWarnings("unused")
    @ShellMethod(value = "Run migration", key = "run")
    @ShellMethodAvailability(value = "isAvailableToRun")
    public void startMigrationJob() {
        jobService.runJob();
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "Restart migration job", key = "restart")
    @ShellMethodAvailability(value = "isAvailableToRestart")
    public void restartMigrationJob() {
        jobService.restartJob();
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "Show info about job's execution", key = "info")
    @ShellMethodAvailability(value = "isAvailableToRerun")
    public String showJobInfo() {
        return jobService.showJobInfo();
    }

    @SuppressWarnings("unused")
    private Availability isAvailableToRun() {
        return jobService.getExecutionId() == null ? Availability.available() :
                Availability.unavailable("Job was completed. Type \"restart\" to restart job.");
    }

    @SuppressWarnings("unused")
    private Availability isAvailableToRerun() {
        return jobService.getExecutionId() != null ? Availability.available() :
                Availability.unavailable("Job wasn't started yet. Type command \"run\" to run job at first time.");
    }
}
