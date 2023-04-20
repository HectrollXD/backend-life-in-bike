package mx.com.hexlink.es.lifeinbike.commondata.models.repositories;

import java.util.UUID;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import mx.com.hexlink.es.lifeinbike.commondata.models.Person;



public interface PersonRepository extends CrudRepository<Person, UUID>{
    List<Person> findAll();
}
