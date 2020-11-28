package ru.alexsumin.springcourse.config.steps;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.alexsumin.springcourse.domain.document.AuthorDocument;
import ru.alexsumin.springcourse.domain.relational.Author;
import ru.alexsumin.springcourse.service.EntityToDocumentMapperService;

import javax.persistence.EntityManagerFactory;


@RequiredArgsConstructor
@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorMigrationStepConfig {
    EntityManagerFactory managerFactory;
    MongoTemplate mongoTemplate;
    EntityToDocumentMapperService service;
    StepBuilderFactory stepBuilderFactory;

    @StepScope
    @Bean
    public JpaPagingItemReader<Author> authorReader() {
        return new JpaPagingItemReaderBuilder<Author>()
                .name("authorReader")
                .entityManagerFactory(managerFactory)
                .queryString("select a from Author a")
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<Author, AuthorDocument> authorProcessor() {
        return service::toDocument;
    }

    @StepScope
    @Bean
    public MongoItemWriter<AuthorDocument> authorWriter() {
        return new MongoItemWriterBuilder<AuthorDocument>()
                .collection("author")
                .template(mongoTemplate)
                .build();
    }

    @Bean
    public Step authorMigrationStep(JpaPagingItemReader<Author> authorReader,
                                    ItemProcessor<Author, AuthorDocument> authorProcessor,
                                    MongoItemWriter<AuthorDocument> authorWriter) {
        return stepBuilderFactory.get("authorMigrationStep")
                .<Author, AuthorDocument>chunk(3)
                .reader(authorReader)
                .processor(authorProcessor)
                .writer(authorWriter)
                .allowStartIfComplete(Boolean.TRUE)
                .build();
    }

}
