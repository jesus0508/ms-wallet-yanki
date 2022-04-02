package pe.com.project4.ms.infrastructure.persistence.mongodb;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pe.com.project4.ms.application.repository.WalletYankiRepository;
import pe.com.project4.ms.domain.WalletYanki;
import pe.com.project4.ms.infrastructure.persistence.model.WalletYankiDao;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class WalletYankiReactiveMongoRepository implements WalletYankiRepository {

    private final IWalletYankiReactiveMongoRepository walletYankiReactiveMongoRepository;

    @Override
    public Mono<WalletYanki> save(WalletYanki walletYanki) {
        return walletYankiReactiveMongoRepository.save(new WalletYankiDao(walletYanki))
                .map(WalletYankiDao::toWalletYanki);
    }

}
