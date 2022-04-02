package pe.com.project4.ms.application.repository;

import pe.com.project4.ms.domain.WalletYanki;
import reactor.core.publisher.Mono;

public interface WalletYankiRepository {
    Mono<WalletYanki> save(WalletYanki walletYanki);
}
