package pe.com.project4.ms.application;

import pe.com.project4.ms.domain.WalletYanki;
import pe.com.project4.ms.infrastructure.event.SendYankiEvent;
import reactor.core.publisher.Mono;

public interface SaveWalletYankiUseCase {
    Mono<WalletYanki> save(SendYankiEvent sendMoneyEvent);
}
