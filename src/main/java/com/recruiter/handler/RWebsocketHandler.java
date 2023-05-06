package com.recruiter.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RWebsocketHandler implements WebSocketHandler {

    @Autowired
    @Qualifier("WS_SESSION")
    private ConcurrentHashMap<String, WebSocketSession> wsSession;

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        String clientId = session.getId();
        wsSession.put(clientId, session);
        return session.receive().then().and(session.closeStatus().doFinally(signalType -> {
            wsSession.remove(clientId);
        })).then();
    }
}
