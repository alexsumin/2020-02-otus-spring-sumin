package ru.alexsumin.springcourse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;
import ru.alexsumin.springcourse.domain.ToDo;
import ru.alexsumin.springcourse.dto.ToDoDTO;
import ru.alexsumin.springcourse.mapper.ToDoMapper;
import ru.alexsumin.springcourse.repository.ToDoRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ToDoServiceImpl implements ToDoService {
    private ToDoMapper mapper;
    private ToDoRepository repository;
    private MessageChannel msgChannel;

    @Autowired
    public ToDoServiceImpl(ToDoMapper mapper, ToDoRepository repository, @Qualifier("ftpChannel") MessageChannel msgChannel) {
        this.mapper = mapper;
        this.repository = repository;
        this.msgChannel = msgChannel;
    }

    @Override
    public ToDo create(ToDoDTO toDoDTO) {
        ToDo saved = repository.save(mapper.toEntity(toDoDTO));
        var msg = MessageBuilder
                .withPayload(toDoDTO.toString())
                .setHeader("targetFileName", toDoDTO.getAssigner() + "_" + LocalDateTime.now() + ".txt")
                .build();
        msgChannel.send(msg);
        return saved;
    }
}
