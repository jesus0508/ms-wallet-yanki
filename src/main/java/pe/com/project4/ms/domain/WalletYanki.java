package pe.com.project4.ms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletYanki {
    private String id;
    private BigDecimal saldo;
    private String nuncelular;
    private LocalDateTime fechregistry;
}
