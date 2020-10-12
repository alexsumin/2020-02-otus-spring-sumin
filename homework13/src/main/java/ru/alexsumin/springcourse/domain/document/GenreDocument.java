package ru.alexsumin.springcourse.domain.document;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("genres")
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(exclude = "id")
@NoArgsConstructor
@AllArgsConstructor
public class GenreDocument {
    @Id
    String id;
    String name;
}
