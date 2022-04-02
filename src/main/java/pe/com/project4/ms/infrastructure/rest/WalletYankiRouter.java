package pe.com.project4.ms.infrastructure.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class WalletYankiRouter {
    private static final String WALLET_HOLDERS = "/wallet-yanki";
    private static final String WALLET_HOLDER = "/wallet-account";
    private static final String WALLET_HOLDER1 = "/wallet-transaction";

    @Bean
    public RouterFunction<ServerResponse> routes(WalletYankiHandler walletYankiHandler) {
        return route(POST(WALLET_HOLDERS).and(accept(APPLICATION_JSON)), walletYankiHandler::postWalletYanki);
    }
}
