package pe.com.project4.ms.infrastructure.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.com.project4.ms.domain.WalletYanki;
import pe.com.project4.ms.infrastructure.event.SendYankiEvent;
import pe.com.project4.ms.infrastructure.persistence.service.YankiService;
import pe.com.project4.ms.infrastructure.producer.WalletYankiKafkaProducer;
import reactor.core.CorePublisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class WalletYankiHandler {

    private final WalletYankiKafkaProducer walletYankiKafkaProducer;
    private  YankiService YankiService;

    public Mono<ServerResponse> postWalletYanki(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(SendYankiEvent.class)
                .map(sendMoneyEvent -> {
                  walletYankiKafkaProducer.sendMessage(sendMoneyEvent);
                  System.out.println("llego uuuu"+sendMoneyEvent.getNuncelular());
                    return sendMoneyEvent;
                }).then(ServerResponse.noContent().build());
    }



  public Mono<ServerResponse> postCustomer(ServerRequest request) {
    return request.bodyToMono(WalletYanki.class)
            .map(YankiService::save)
            .flatMap(yankiMono -> this.toServerResponse(yankiMono, HttpStatus.CREATED));
  }

  public Mono<ServerResponse> getCustomerById(ServerRequest request) {
    Mono<WalletYanki> yankiMono = YankiService.findById(request.pathVariable("id"));
    return this.toServerResponse(yankiMono, HttpStatus.OK);
  }

  public Mono<ServerResponse> getAllCustomers(ServerRequest request) {
    Flux<WalletYanki> yankiMono = YankiService.findAll();
    return this.toServerResponse(yankiMono, HttpStatus.OK);
  }

  public Mono<ServerResponse> putCustomer(ServerRequest request) {
    return request.bodyToMono(WalletYanki.class)
            .map(yankiMono -> YankiService.update(yankiMono, request.pathVariable("id")))
            .flatMap(yankiMono -> this.toServerResponse(yankiMono, HttpStatus.OK));
  }

  public Mono<ServerResponse> deleteCustomer(ServerRequest request) {
    return YankiService.deleteById(request.pathVariable("id"))
            .then(ServerResponse.noContent().build());
  }

  private Mono<ServerResponse> toServerResponse(CorePublisher<WalletYanki> yankiMono, HttpStatus status) {
    return ServerResponse
            .status(status)
            .contentType(MediaType.APPLICATION_JSON)
            .body(yankiMono, WalletYanki.class);
  }
}
