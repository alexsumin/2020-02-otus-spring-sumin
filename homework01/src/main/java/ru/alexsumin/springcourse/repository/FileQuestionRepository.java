package ru.alexsumin.springcourse.repository;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBeanBuilder;
import ru.alexsumin.springcourse.domain.Question;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class FileQuestionRepository implements QuestionRepository {
    private final String fileName;

    public FileQuestionRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Question> getQuestions() {
        return loadQuestions();
    }

    private Path getFilePath() throws URISyntaxException, IOException {
        URI uri = Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).toURI();
        Path path;
        if (uri.getScheme().equals("jar")) {
            FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
            path = fileSystem.getPath(fileName);
        } else {
            path = Paths.get(uri);
        }
        return path;

    }

    private List<Question> loadQuestions() {
        try (var reader = Files.newBufferedReader(getFilePath(), StandardCharsets.UTF_8)) {

            ColumnPositionMappingStrategy<Question> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Question.class);

            var csvToBean = new CsvToBeanBuilder<Question>(reader)
                    .withType(Question.class)
                    .withMappingStrategy(strategy)
                    .withSeparator(';')
                    .build();

            return csvToBean.parse();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
