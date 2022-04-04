package pe.com.project4.ms.infrastructure.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import javax.ws.rs.GET;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class WalletYankiRouter {
    private static final String WALLET_YANKI = "/wallet-yanki";
    private static final String WALLET_HOLDER = WALLET_YANKI + "/account";
    private static final String WALLET_HOLDER1 = WALLET_YANKI + "/transaction";
    private static final String WALLET = WALLET_YANKI + "/number";

    @Bean
    public RouterFunction<ServerResponse> routes(WalletYankiHandler walletYankiHandler) {
        return route(POST(WALLET_HOLDER).and(accept(APPLICATION_JSON)), walletYankiHandler::postWalletYanki)
                .andRoute(POST(WALLET_HOLDER1).and(accept(APPLICATION_JSON)), walletYankiHandler::postTransaction)
                .andRoute(RequestPredicates.GET(WALLET).and(accept(APPLICATION_JSON)), walletYankiHandler::getYankiPhone);
    }
}
