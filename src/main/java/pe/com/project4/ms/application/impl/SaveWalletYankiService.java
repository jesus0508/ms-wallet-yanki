package pe.com.project4.ms.application.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.com.project4.ms.application.SaveWalletYankiUseCase;
import pe.com.project4.ms.application.repository.WalletYankiRepository;
import pe.com.project4.ms.domain.WalletYanki;
import pe.com.project4.ms.infrastructure.event.SendYankiEvent;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveWalletYankiService implements SaveWalletYankiUseCase {

    private final WalletYankiRepository walletYankiRepository;

    @Override
    public Mono<WalletYanki> save(SendYankiEvent sendMoneyEvent) {
        return walletYankiRepository.save(sendMoneyEvent.toWalletTransaction());
    }

}
