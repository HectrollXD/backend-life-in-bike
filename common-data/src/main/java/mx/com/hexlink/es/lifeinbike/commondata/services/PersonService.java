package mx.com.hexlink.es.lifeinbike.commondata.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import mx.com.hexlink.es.lifeinbike.commondata.exceptions.DatabaseExeption.DeleteDataExeption;
import mx.com.hexlink.es.lifeinbike.commondata.exceptions.DatabaseExeption.RetriveDataExeption;
import mx.com.hexlink.es.lifeinbike.commondata.exceptions.DatabaseExeption.SaveDataExeption;
import mx.com.hexlink.es.lifeinbike.commondata.models.Person;
import mx.com.hexlink.es.lifeinbike.commondata.models.repositories.PersonRepository;



@Service
public class PersonService implements IBasicServiceOperation<Person, UUID>{
    private static final Logger LOG = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private PersonRepository personRepository;



    @Override
    @Transactional
    public Person addNewRegister(Person register) throws SaveDataExeption {
        LOG.info("Start service add new person.");

        Person person = null;

        try{
            person = personRepository.save(register);

            if( person == null ){
                LOG.warn("The save method retrive a null object.");
            }
            else{
                LOG.info("The person was addedd successfully.");
            }
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to add new person.", ex);
            throw new SaveDataExeption(ex.getMessage(), ex);
        }

        return person;
    }

    
    @Override
    @Transactional(readOnly = true)
    public List<Person> getAllRegisters() throws RetriveDataExeption {
        LOG.info("Start service get all persons.");

        List<Person> person = null;

        try{
            person = personRepository.findAll();
            LOG.info("All persons has retrived successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to get all persons.", ex);
            throw new RetriveDataExeption(ex.getMessage(), ex);
        }

        return person;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Person> findARegisterByID(UUID id) throws RetriveDataExeption {
        LOG.info("Start service get person by ID.");

        Optional<Person> person = null;

        try{
            person = personRepository.findById(id);
            LOG.info("The person has retrived successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to get all persons.", ex);
            throw new RetriveDataExeption(ex.getMessage(), ex);
        }

        return person;
    }


    @Override
    @Transactional
    public Person updateARegister(Person register) throws SaveDataExeption {
        LOG.info("Start service update person.");

        Person person = null;

        try{
            register.setLastModifed(LocalDateTime.now());
            person = personRepository.save(register);

            if( person == null ){
                LOG.warn("The update method retrive a null object.");
            }
            else{
                LOG.info("The person was update successfully.");
            }
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to update person.", ex);
            throw new SaveDataExeption(ex.getMessage(), ex);
        }

        return person;
    }


    @Override
    @Transactional
    public void deleteARegisterByID(Person register) throws DeleteDataExeption {
        LOG.info("Start service delete person.");

        try{
            personRepository.delete(register);
            LOG.info("The person was deleted successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to delete person.", ex);
            throw new DeleteDataExeption(ex.getMessage(), ex);
        }
    }
}
