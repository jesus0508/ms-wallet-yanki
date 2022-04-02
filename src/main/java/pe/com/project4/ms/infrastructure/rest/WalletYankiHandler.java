package pe.com.project4.ms.infrastructure.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.com.project4.ms.application.impl.SaveWalletYankiService;
import pe.com.project4.ms.domain.WalletYanki;
import pe.com.project4.ms.infrastructure.rest.request.CreateAccountRequest;
import reactor.core.CorePublisher;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class WalletYankiHandler {

    private final SaveWalletYankiService saveWalletYankiService;

    public Mono<ServerResponse> postWalletYanki(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CreateAccountRequest.class)
                .map(saveWalletYankiService::createAccount)
                .flatMap(respuesta -> this.toServerResponse(respuesta, HttpStatus.CREATED));
    }

    public Mono<ServerResponse> postTransaction(ServerRequest serverRequest) {
        return null;
    }

    private Mono<ServerResponse> toServerResponse(CorePublisher<WalletYanki> yankiMono, HttpStatus status) {
    	log.info("Antes de responder {}");

        return ServerResponse
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(yankiMono, WalletYanki.class);
    }

}
