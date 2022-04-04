package pe.com.project4.ms.infrastructure.persistence.mongodb;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;
import pe.com.project4.ms.application.repository.WalletYankiRepository;
import pe.com.project4.ms.domain.WalletYanki;
import pe.com.project4.ms.infrastructure.persistence.model.WalletYankiDao;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
@Slf4j
public class WalletYankiReactiveMongoRepository implements WalletYankiRepository{

    private final IWalletYankiReactiveMongoRepository walletYankiReactiveMongoRepository;

    @Override
    public Mono<WalletYanki> save(WalletYanki walletYanki) {
    	log.info("==> Dentro del repository {}", walletYanki);
        return walletYankiReactiveMongoRepository.save(new WalletYankiDao(walletYanki))
                .map(WalletYankiDao::toWalletYanki)
                .doOnNext(result -> log.info( "==> Despues del map {}", result));
    }

    @Override
    public Mono<WalletYanki> findByPhone(String phone) {
        log.info("==> llega a buscar {}", phone);
        return walletYankiReactiveMongoRepository
                .findByNumPhone(phone)
                .map(WalletYankiDao::busquedaYanqui)
                .doOnNext(result -> log.info( "==> llega a encontrar {}", result));
    }


}
