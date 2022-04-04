package pe.com.project4.ms.application.repository;

import pe.com.project4.ms.domain.WalletYanki;
import pe.com.project4.ms.infrastructure.persistence.model.WalletYankiDao;
import reactor.core.publisher.Mono;

public interface WalletYankiRepository {
    Mono<WalletYanki> save(WalletYanki walletYanki);
    Mono<WalletYanki> findByPhone(String phone);
}
