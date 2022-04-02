package pe.com.project4.ms.infrastructure.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pe.com.project4.ms.application.SaveWalletYankiUseCase;
import pe.com.project4.ms.infrastructure.event.SendYankiEvent;

@Component
@Slf4j
@RequiredArgsConstructor
public class WalletYankiKafkaConsumer {

    private final SaveWalletYankiUseCase saveWalletYankiUseCase;

    @KafkaListener(topics = "${spring.kafka.consumer.topic.name}", groupId = "TRANSACTION")
    public void consume(SendYankiEvent sendMoneyEvent) {
        log.debug("Consuming Message {}", sendMoneyEvent);
        saveWalletYankiUseCase.save(sendMoneyEvent).subscribe(result -> log.debug(result.toString()));
    }

}
