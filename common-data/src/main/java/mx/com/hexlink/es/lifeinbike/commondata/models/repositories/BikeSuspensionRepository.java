package mx.com.hexlink.es.lifeinbike.commondata.models.repositories;

import java.util.UUID;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import mx.com.hexlink.es.lifeinbike.commondata.models.BikeSuspension;



public interface BikeSuspensionRepository extends CrudRepository<BikeSuspension, UUID>{
    List<BikeSuspension> findAll();
}
