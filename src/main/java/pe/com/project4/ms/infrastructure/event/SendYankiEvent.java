package pe.com.project4.ms.infrastructure.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.project4.ms.domain.WalletYanki;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendYankiEvent {
    private String id;
    private BigDecimal saldo;
    private String nuncelular;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fechregistry = LocalDateTime.now();

    public WalletYanki toWalletTransaction() {
        WalletYanki walletyanki = new WalletYanki();
        walletyanki.setSaldo(saldo);
        walletyanki.setNuncelular(nuncelular);
        walletyanki.setFechregistry(fechregistry);
        return walletyanki;
    }
}
