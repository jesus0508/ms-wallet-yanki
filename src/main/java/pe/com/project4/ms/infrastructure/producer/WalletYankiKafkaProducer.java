package pe.com.project4.ms.infrastructure.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pe.com.project4.ms.infrastructure.event.SendYankiEvent;

@Component
@Slf4j
public class WalletYankiKafkaProducer {
    private final KafkaTemplate<String, SendYankiEvent> kafkaTemplate;

    public WalletYankiKafkaProducer(KafkaTemplate<String, SendYankiEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendAccountCreate(SendYankiEvent sendMoneyEvent) {
        log.debug(" Producing message {}", sendMoneyEvent.toString());
        kafkaTemplate.send("WALLET-ACCOUNT-CREATED", sendMoneyEvent);
    }

    public void sendMoney(SendYankiEvent sendMoneyEvent) {
        log.debug(" Producing message {}", sendMoneyEvent.toString());
        kafkaTemplate.send("WALLET-SEND-MONEY", sendMoneyEvent);
    }

//    public void sendMessages(SendMoneyEvent sendMoneyEvent) {
//        log.info("crear cuenta {}", sendMoneyEvent);
//        this.kafkaTemplate.send("WALLET-ACCOUNT-CREATED", sendMoneyEvent);
//    }
//    public void sendDinner(SendMoneyEvent sendMoneyEvent) {
//        log.info("Envio dinero {}", sendMoneyEvent);
//        this.kafkaTemplate.send("WALLET-SEND-MONEY", sendMoneyEvent);
//    }

}
