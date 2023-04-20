package mx.com.hexlink.es.lifeinbike.commondata.models.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import mx.com.hexlink.es.lifeinbike.commondata.models.BikeBreak;



public interface BikeBreakRepository extends
    PagingAndSortingRepository<BikeBreak, UUID>, CrudRepository<BikeBreak, UUID>
{
    List<BikeBreak> findAll();
}
