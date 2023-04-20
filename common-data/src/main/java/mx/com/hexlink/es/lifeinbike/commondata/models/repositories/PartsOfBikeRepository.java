package mx.com.hexlink.es.lifeinbike.commondata.models.repositories;

import java.util.UUID;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import mx.com.hexlink.es.lifeinbike.commondata.models.PartsOfBike;



public interface PartsOfBikeRepository extends CrudRepository<PartsOfBike, UUID>{
    List<PartsOfBike> findAll();
}
