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
import ru.alexsumin.springcourse.domain.document.BookDocument;
import ru.alexsumin.springcourse.domain.relational.Book;
import ru.alexsumin.springcourse.service.EntityToDocumentMapperService;

import javax.persistence.EntityManagerFactory;
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookMigrationStepConfig {
    EntityManagerFactory managerFactory;
    MongoTemplate mongoTemplate;
    EntityToDocumentMapperService service;
    StepBuilderFactory stepBuilderFactory;

    @StepScope
    @Bean
    public JpaPagingItemReader<Book> bookReader() {
        return new JpaPagingItemReaderBuilder<Book>()
                .name("bookReader")
                .entityManagerFactory(managerFactory)
                .queryString("select b from Book b")
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<Book, BookDocument> bookProcessor() {
        return service::toDocument;
    }

    @StepScope
    @Bean
    public MongoItemWriter<BookDocument> bookWriter() {
        return new MongoItemWriterBuilder<BookDocument>()
                .collection("book")
                .template(mongoTemplate)
                .build();
    }

    @Bean
    public Step bookMigrationStep(JpaPagingItemReader<Book> bookReader,
                                  ItemProcessor<Book, BookDocument> bookProcessor,
                                  MongoItemWriter<BookDocument> bookWriter) {
        return stepBuilderFactory.get("bookMigrationStep")
                .<Book, BookDocument>chunk(3)
                .reader(bookReader)
                .processor(bookProcessor)
                .writer(bookWriter)
                .allowStartIfComplete(Boolean.TRUE)
                .build();
    }

}
