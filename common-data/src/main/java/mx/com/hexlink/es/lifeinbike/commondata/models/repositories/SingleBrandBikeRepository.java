package mx.com.hexlink.es.lifeinbike.commondata.models.repositories;

import java.util.UUID;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import mx.com.hexlink.es.lifeinbike.commondata.models.SingleBrandBike;



public interface SingleBrandBikeRepository extends CrudRepository<SingleBrandBike, UUID>{
    List<SingleBrandBike> findAll();
}
