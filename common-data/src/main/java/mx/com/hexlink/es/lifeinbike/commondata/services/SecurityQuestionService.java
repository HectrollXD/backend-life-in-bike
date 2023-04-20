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
import mx.com.hexlink.es.lifeinbike.commondata.models.SecurityQuestion;
import mx.com.hexlink.es.lifeinbike.commondata.models.repositories.SecurityQuestionRepository;



@Service
public class SecurityQuestionService implements IBasicServiceOperation<SecurityQuestion, UUID>{
    private static final Logger LOG = LoggerFactory.getLogger(SecurityQuestionService.class);

    @Autowired
    private SecurityQuestionRepository securityQuestionRepository;



    @Transactional
    public List<SecurityQuestion> addMultipleRegisters(List<SecurityQuestion> questions) throws SaveDataExeption{
        LOG.info("Start service addMultipleRegisters.");

        Iterable<SecurityQuestion> securityQuestion;

        try{
            securityQuestion = (List<SecurityQuestion>) securityQuestionRepository.saveAll(questions);

            if( securityQuestion == null ){
                LOG.warn("The save method retrive a null object.");
            }
            else{
                LOG.info("The security question was addedd successfully.");
            }
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to add new security question.", ex);
            throw new SaveDataExeption(ex.getMessage(), ex);
        }

        return (List<SecurityQuestion>) securityQuestion;
    }


    @Override
    @Transactional
    public SecurityQuestion addNewRegister(SecurityQuestion register) throws SaveDataExeption {
        LOG.info("Start service add new securityQuestion.");

        SecurityQuestion securityQuestion = null;

        try{
            securityQuestion = securityQuestionRepository.save(register);

            if( securityQuestion == null ){
                LOG.warn("The save method retrive a null object.");
            }
            else{
                LOG.info("The security question was addedd successfully.");
            }
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to add new security question.", ex);
            throw new SaveDataExeption(ex.getMessage(), ex);
        }

        return securityQuestion;
    }

    
    @Override
    @Transactional(readOnly = true)
    public List<SecurityQuestion> getAllRegisters() throws RetriveDataExeption {
        LOG.info("Start service get all security questions.");

        List<SecurityQuestion> securityQuestion = null;

        try{
            securityQuestion = securityQuestionRepository.findAll();
            LOG.info("All security questions has retrived successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to get all security questions.", ex);
            throw new RetriveDataExeption(ex.getMessage(), ex);
        }

        return securityQuestion;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SecurityQuestion> findARegisterByID(UUID id) throws RetriveDataExeption {
        LOG.info("Start service get securityQuestion by ID.");

        Optional<SecurityQuestion> securityQuestion = null;

        try{
            securityQuestion = securityQuestionRepository.findById(id);
            LOG.info("The security question has retrived successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to get all security questions.", ex);
            throw new RetriveDataExeption(ex.getMessage(), ex);
        }

        return securityQuestion;
    }


    @Override
    @Transactional
    public SecurityQuestion updateARegister(SecurityQuestion register) throws SaveDataExeption {
        LOG.info("Start service update securityQuestion.");

        SecurityQuestion securityQuestion = null;

        try{
            register.setLastModifed(LocalDateTime.now());
            securityQuestion = securityQuestionRepository.save(register);

            if( securityQuestion == null ){
                LOG.warn("The update method retrive a null object.");
            }
            else{
                LOG.info("The security question was update successfully.");
            }
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to update security question.", ex);
            throw new SaveDataExeption(ex.getMessage(), ex);
        }

        return securityQuestion;
    }


    @Override
    @Transactional
    public void deleteARegisterByID(SecurityQuestion register) throws DeleteDataExeption {
        LOG.info("Start service delete securityQuestion.");

        try{
            securityQuestionRepository.delete(register);
            LOG.info("The security question was deleted successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to delete security question.", ex);
            throw new DeleteDataExeption(ex.getMessage(), ex);
        }
    }
}
