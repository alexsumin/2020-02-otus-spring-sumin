package ru.alexsumin.springcourse.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Slf4j
@RequiredArgsConstructor
@Configuration
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JobConfig {
    JobBuilderFactory jobBuilderFactory;
    StepBuilderFactory stepBuilderFactory;
    MongoTemplate mongoTemplate;

    @Bean
    public TaskletStep dropMongoDBStep() {
        return stepBuilderFactory
                .get("dropMongoDBStep")
                .tasklet((stepContribution, chunkContext) -> {
                    mongoTemplate.getDb().drop();
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Job migrateJpaToMongoJob(Step authorMigrationStep,
                                    Step genreMigrationStep,
                                    Step bookMigrationStep) {
        return jobBuilderFactory.get("migrateJpaToMongoJob")
                .incrementer(jobParameters -> jobParameters)
                .start(dropMongoDBStep())
                .next(authorMigrationStep)
                .next(genreMigrationStep)
                .next(bookMigrationStep)
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        log.info("Migration going to start.");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        log.info("Migration have been finished");
                    }
                })
                .build();
    }

}
