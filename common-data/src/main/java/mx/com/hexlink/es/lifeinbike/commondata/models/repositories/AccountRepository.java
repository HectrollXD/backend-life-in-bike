package mx.com.hexlink.es.lifeinbike.commondata.models.repositories;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import mx.com.hexlink.es.lifeinbike.commondata.models.Account;



public interface AccountRepository extends CrudRepository<Account, UUID>{
}
