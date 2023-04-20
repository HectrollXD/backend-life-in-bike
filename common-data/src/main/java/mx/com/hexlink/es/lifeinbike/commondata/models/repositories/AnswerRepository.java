package mx.com.hexlink.es.lifeinbike.commondata.models.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import mx.com.hexlink.es.lifeinbike.commondata.models.Answer;



public interface AnswerRepository extends CrudRepository<Answer, UUID>{
    List<Answer> findAll();
}
