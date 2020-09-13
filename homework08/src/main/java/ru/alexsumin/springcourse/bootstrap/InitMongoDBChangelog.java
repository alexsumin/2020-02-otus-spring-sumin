package ru.alexsumin.springcourse.bootstrap;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.alexsumin.springcourse.domain.Author;
import ru.alexsumin.springcourse.domain.Book;
import ru.alexsumin.springcourse.domain.Genre;
import ru.alexsumin.springcourse.util.ProjectUtil;

import java.util.Set;

@ChangeLog
public class InitMongoDBChangelog {

    @ChangeSet(order = "000", id = "dropDB", author = "alexsumin", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "alexsumin", runAlways = true)
    public void initTestData(MongoTemplate template) {
        var eckel = Author.builder().name("Bruce Eckel").build();
        var littell = Author.builder().name("Jonathan Littell").build();
        var tolstoy = Author.builder().name("Lev Nikolayevich Tolstoy").build();

        template.save(eckel);
        template.save(littell);
        template.save(tolstoy);

        var technicalLiterature = Genre.builder().name("Technical literature").build();
        var novel = Genre.builder().name("Novel").build();

        template.save(technicalLiterature);
        template.save(novel);

        var thinkingInJava = Book.builder()
                .title("Thinking in Java")
                .published(ProjectUtil.toDate("2006-09-13"))
                .author(eckel)
                .genre(technicalLiterature)
                .build();
        var theKindlyOnes = Book.builder()
                .title("The Kindly Ones")
                .published(ProjectUtil.toDate("2006-02-20"))
                .author(littell)
                .genre(novel)
                .build();
        var onJava8 = Book.builder()
                .title("On Java8")
                .published(ProjectUtil.toDate("2013-01-01"))
                .author(eckel)
                .genre(technicalLiterature)
                .comments(Set.of("From the author of the cult Thinking in Java, Recommend!"))
                .build();
        var warAndPeace = Book.builder()
                .title("War and Peace")
                .published(ProjectUtil.toDate("1869-01-01"))
                .author(tolstoy)
                .genre(novel)
                .comments(Set.of("Going to read"))
                .build();
        template.insert(thinkingInJava);
        template.insert(theKindlyOnes);
        template.insert(onJava8);
        template.insert(warAndPeace);
    }
}
