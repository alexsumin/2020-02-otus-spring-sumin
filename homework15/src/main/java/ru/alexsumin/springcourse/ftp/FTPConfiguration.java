package ru.alexsumin.springcourse.ftp;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.file.remote.gateway.AbstractRemoteFileOutboundGateway;
import org.springframework.integration.file.remote.session.DelegatingSessionFactory;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.ftp.dsl.Ftp;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.integration.ftp.session.FtpRemoteFileTemplate;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageChannel;
import ru.alexsumin.springcourse.ftp.properties.FTPProperties;

import java.util.Map;

@Slf4j
@Configuration
public class FTPConfiguration {

    @Bean
    FtpRemoteFileTemplate ftpRemoteFileTemplate(DefaultFtpSessionFactory dsf) {
        var ftpRemoteFileTemplate = new FtpRemoteFileTemplate(dsf);
        ftpRemoteFileTemplate.setRemoteDirectoryExpression(new LiteralExpression(""));
        ftpRemoteFileTemplate.setFileNameGenerator(message -> (String) message.getHeaders().get("targetFileName"));
        return ftpRemoteFileTemplate;
    }

    @Bean("ftpChannel")
    public MessageChannel incoming() {
        return MessageChannels.direct().get();
    }

    @Bean
    IntegrationFlow gateway(FtpRemoteFileTemplate template, DelegatingSessionFactory<FTPFile> dsf) {
        return f -> f
                .channel(incoming())
                .handle((GenericHandler<Object>) (key, messageHeaders) -> {
                    dsf.setThreadKey(key);
                    return key;
                })
                .handle(Ftp
                        .outboundGateway(template, AbstractRemoteFileOutboundGateway.Command.PUT, "payload")
                        .fileExistsMode(FileExistsMode.IGNORE)
                        .options(AbstractRemoteFileOutboundGateway.Option.RECURSIVE)
                )
                .handle((GenericHandler<Object>) (key, messageHeaders) -> {
                    dsf.clearThreadKey();
                    return null;
                });
    }

    @Bean
    public DefaultFtpSessionFactory createSessionFactory(FTPProperties properties) {
        var defaultFtpSessionFactory = new DefaultFtpSessionFactory();
        defaultFtpSessionFactory.setPassword(properties.getPassword());
        defaultFtpSessionFactory.setUsername(properties.getUser());
        defaultFtpSessionFactory.setHost(properties.getHost());
        defaultFtpSessionFactory.setPort(properties.getPort());
        return defaultFtpSessionFactory;
    }

    @Bean
    DelegatingSessionFactory<FTPFile> dsf(Map<String, DefaultFtpSessionFactory> ftpSessionFactories) {
        return new DelegatingSessionFactory<>(ftpSessionFactories::get);
    }

}
