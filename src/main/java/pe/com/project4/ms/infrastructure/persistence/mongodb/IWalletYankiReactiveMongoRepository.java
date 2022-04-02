package pe.com.project4.ms.infrastructure.persistence.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pe.com.project4.ms.infrastructure.persistence.model.WalletYankiDao;

public interface IWalletYankiReactiveMongoRepository extends ReactiveMongoRepository<WalletYankiDao, String> {

}
