package com.recruiter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recruiter.mapper.NotificationDataMapper;
import com.recruiter.model.NotificationData;
import com.recruiter.model.NotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/rsock")
public class MainController {

    @Autowired
    @Qualifier("WS_SESSION")
    private ConcurrentHashMap<String, WebSocketSession> wsSession;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private NotificationDataMapper notificationDataMapper;

    @PostMapping
    public Mono<String> sendMessage(@RequestBody NotificationRequest event) throws JsonProcessingException {
        if (wsSession.containsKey(event.getSessionId())) {
            WebSocketSession webSocketSession = wsSession.get(event.getSessionId());
            return webSocketSession.send(Mono.just(webSocketSession.textMessage(mapper.writeValueAsString(notificationDataMapper.toNotificationData(event))))).thenReturn("OK");
        } else
            return Mono.just("NOK");
    }

    @GetMapping
    public Mono<List<String>> getListSession() {
        List<String> keys = new ArrayList<>(wsSession.keySet());
        return Mono.just(keys);
    }


}
