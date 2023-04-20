package mx.com.hexlink.es.lifeinbike.commondata.models.repositories;

import java.util.UUID;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import mx.com.hexlink.es.lifeinbike.commondata.models.BikeImage;



public interface BikeImageRepository extends CrudRepository<BikeImage, UUID>{
    List<BikeImage> findAll();
}
