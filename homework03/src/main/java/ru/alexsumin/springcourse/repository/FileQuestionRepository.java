package ru.alexsumin.springcourse.repository;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.alexsumin.springcourse.components.QuestionMapper;
import ru.alexsumin.springcourse.domain.Question;
import ru.alexsumin.springcourse.domain.QuizQuestion;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class FileQuestionRepository implements QuestionRepository {
    private final String filePath;
    private final QuestionMapper mapper;

    public FileQuestionRepository(@Value("${app.filepath}") String filePath, QuestionMapper mapper) {
        this.filePath = filePath;
        this.mapper = mapper;
    }

    @Override
    public List<QuizQuestion> getQuestions() {
        return loadQuestions().stream()
                .map(mapper::toQuizQuestion)
                .collect(Collectors.toList());
    }

    private Path getFilePath() throws URISyntaxException, IOException {
        URI uri = Objects.requireNonNull(getClass()
                .getClassLoader()
                .getResource(filePath))
                .toURI();

        if (uri.getScheme().equals("jar")) {
            FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
            return fileSystem.getPath(filePath);
        }

        return Paths.get(uri);
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
