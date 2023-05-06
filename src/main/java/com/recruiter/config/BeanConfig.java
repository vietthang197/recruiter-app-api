package com.recruiter.config;

import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.web.reactive.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

@Configuration
public class BeanConfig {

    @Bean("WS_SESSION")
    public ConcurrentHashMap<String, WebSocketSession> wsSession() {
        return new ConcurrentHashMap<>();
    }
}
