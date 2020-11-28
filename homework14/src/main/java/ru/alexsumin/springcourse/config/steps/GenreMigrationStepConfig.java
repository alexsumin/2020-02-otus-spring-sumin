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
import ru.alexsumin.springcourse.domain.document.GenreDocument;
import ru.alexsumin.springcourse.domain.relational.Genre;
import ru.alexsumin.springcourse.service.EntityToDocumentMapperService;

import javax.persistence.EntityManagerFactory;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreMigrationStepConfig {
    EntityManagerFactory managerFactory;
    MongoTemplate mongoTemplate;
    EntityToDocumentMapperService service;
    StepBuilderFactory stepBuilderFactory;

    @StepScope
    @Bean
    public JpaPagingItemReader<Genre> genreReader() {
        return new JpaPagingItemReaderBuilder<Genre>()
                .name("genreReader")
                .entityManagerFactory(managerFactory)
                .queryString("select g from Genre g")
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<Genre, GenreDocument> genreProcessor() {
        return service::toDocument;
    }

    @StepScope
    @Bean
    public MongoItemWriter<GenreDocument> genreWriter() {
        return new MongoItemWriterBuilder<GenreDocument>()
                .collection("genre")
                .template(mongoTemplate)
                .build();
    }

    @Bean
    public Step genreMigrationStep(JpaPagingItemReader<Genre> genreReader,
                                   ItemProcessor<Genre, GenreDocument> genreProcessor,
                                   MongoItemWriter<GenreDocument> genreWriter) {
        return stepBuilderFactory.get("genreMigrationStep")
                .<Genre, GenreDocument>chunk(3)
                .reader(genreReader)
                .processor(genreProcessor)
                .writer(genreWriter)
                .allowStartIfComplete(Boolean.TRUE)
                .build();
    }
}
