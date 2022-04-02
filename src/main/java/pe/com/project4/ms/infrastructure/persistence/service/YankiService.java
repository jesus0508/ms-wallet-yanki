package pe.com.project4.ms.infrastructure.persistence.service;

import pe.com.project4.ms.domain.WalletYanki;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface YankiService {

	Mono<WalletYanki> save(WalletYanki customer);

	Mono<WalletYanki> findById(String id);

	Mono<WalletYanki> findByDocumentNumber(String documentNumber);

	Flux<WalletYanki> findAll();

	Mono<WalletYanki> update(WalletYanki walletYanki, String id);

	Mono<Void> deleteById(String id);

}
