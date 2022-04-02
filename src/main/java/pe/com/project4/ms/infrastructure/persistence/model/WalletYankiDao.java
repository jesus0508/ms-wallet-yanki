package pe.com.project4.ms.infrastructure.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pe.com.project4.ms.domain.WalletYanki;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("walletTransactions")
public class WalletYankiDao {
    @Id
    private String id;
    private BigDecimal saldo;
    private String nuncelular;
    private LocalDateTime fechregistry;


    public WalletYankiDao(WalletYanki walletYanki) {
        id = walletYanki.getId();
        saldo = walletYanki.getSaldo();
        nuncelular = walletYanki.getNuncelular();
        fechregistry = walletYanki.getFechregistry();
    }

    public WalletYanki toWalletYanki() {
        WalletYanki walletYanki = new WalletYanki();
        walletYanki.setSaldo(saldo);
        walletYanki.setNuncelular(nuncelular);
        walletYanki.setFechregistry(fechregistry);
        return walletYanki;
    }

}
