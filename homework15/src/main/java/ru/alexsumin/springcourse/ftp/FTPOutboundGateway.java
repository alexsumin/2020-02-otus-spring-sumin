package ru.alexsumin.springcourse.ftp;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.io.File;

@MessagingGateway
public interface FTPOutboundGateway {

    @Gateway(requestChannel = "ftpChannel")
    void upload(File file);
}
