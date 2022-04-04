package pe.com.project4.ms.application.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.com.project4.ms.application.SaveWalletYankiUseCase;
import pe.com.project4.ms.application.repository.WalletYankiRepository;
import pe.com.project4.ms.domain.WalletYanki;
import pe.com.project4.ms.infrastructure.producer.WalletAccountCreatedProducer;
import pe.com.project4.ms.infrastructure.rest.request.CreateAccountRequest;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveWalletYankiService implements SaveWalletYankiUseCase {

	private final WalletYankiRepository walletYankiRepository;
	private final WalletAccountCreatedProducer walletAccountCreatedProducer;


	@Override
	public Mono<WalletYanki> createAccount(CreateAccountRequest createAccountRequest) {
		log.info("==> Entro al createAccount {} ", createAccountRequest);
//filter
//		log.info("==> encontro cel {} ", walletYankiRepository.findByPhone(createAccountRequest.getPhoneNumber()));

		if (createAccountRequest.getPhoneNumber().length() == 11) {
			if (createAccountRequest.getDocumentNumber().length() == 8) {
				return walletYankiRepository.save(new WalletYanki(createAccountRequest.getPhoneNumber()))
								.map(walletYanki -> {
									walletAccountCreatedProducer.sendAccountCreate(createAccountRequest.toWalletAccountCreatedEvent());
									log.info("Request, {}", createAccountRequest);
									return walletYanki;
								});
			} else {
				log.warn("==> Ingrese Un Numero de DNI Valido., {} " + "=>[" + createAccountRequest.getDocumentNumber() + "]");
			}
		} else {
			log.warn("==> Ingrese Un Numero de Celular Valido., {} " + "=>[" + createAccountRequest.getPhoneNumber() + "]");
		}

		return Mono.empty();
	}


}
