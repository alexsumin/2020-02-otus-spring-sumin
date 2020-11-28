package ru.alexsumin.springcourse.ftp.properties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "service.ftp")
public class FTPProperties {
//    @NotNull String remotedirectory;
    @NotNull String host;
    @NotNull Integer port;
    @NotNull String user;
    @NotNull String password;
}
