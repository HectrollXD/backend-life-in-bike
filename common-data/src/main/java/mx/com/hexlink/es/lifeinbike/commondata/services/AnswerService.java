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
import mx.com.hexlink.es.lifeinbike.commondata.models.Answer;
import mx.com.hexlink.es.lifeinbike.commondata.models.repositories.AnswerRepository;



@Service
public class AnswerService implements IBasicServiceOperation<Answer, UUID>{
    private static final Logger LOG = LoggerFactory.getLogger(AnswerService.class);

    @Autowired
    private AnswerRepository answerRepository;



    @Override
    @Transactional
    public Answer addNewRegister(Answer register) throws SaveDataExeption {
        LOG.info("Start service add new answer.");

        Answer answer = null;

        try{
            answer = answerRepository.save(register);

            if( answer == null ){
                LOG.warn("The save method retrive a null object.");
            }
            else{
                LOG.info("The answer was addedd successfully.");
            }
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to add new answer.", ex);
            throw new SaveDataExeption(ex.getMessage(), ex);
        }

        return answer;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Answer> getAllRegisters() throws RetriveDataExeption {
        LOG.info("Start service get all answers.");

        List<Answer> answer = null;

        try{
            answer = answerRepository.findAll();
            LOG.info("All answers has retrived successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to get all answers.", ex);
            throw new RetriveDataExeption(ex.getMessage(), ex);
        }

        return answer;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Answer> findARegisterByID(UUID id) throws RetriveDataExeption {
        LOG.info("Start service get answer by ID.");

        Optional<Answer> answer = null;

        try{
            answer = answerRepository.findById(id);
            LOG.info("The answer has retrived successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to get all answers.", ex);
            throw new RetriveDataExeption(ex.getMessage(), ex);
        }

        return answer;
    }

    @Override
    @Transactional
    public Answer updateARegister(Answer register) throws SaveDataExeption {
        LOG.info("Start service update answer.");

        Answer answer = null;

        try{
            register.setLastModifed(LocalDateTime.now());
            answer = answerRepository.save(register);

            if( answer == null ){
                LOG.warn("The update method retrive a null object.");
            }
            else{
                LOG.info("The answer was update successfully.");
            }
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to update answer.", ex);
            throw new SaveDataExeption(ex.getMessage(), ex);
        }

        return answer;
    }

    @Override
    @Transactional
    public void deleteARegisterByID(Answer register) throws DeleteDataExeption {
        LOG.info("Start service delete answer.");

        try{
            answerRepository.delete(register);
            LOG.info("The answer was deleted successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to delete answer.", ex);
            throw new DeleteDataExeption(ex.getMessage(), ex);
        }
    }

    @Transactional
    public List<Answer> addMultipleRegisters(List<Answer> answersToAdd) throws SaveDataExeption{
        LOG.info("Start service addMultipleRegisters.");

        Iterable<Answer> answers;

        try{
            answers = (List<Answer>) answerRepository.saveAll(answersToAdd);

            if( answers == null ){
                LOG.warn("The save method retrive a null object.");
            }
            else{
                LOG.info("The answers was addedd successfully.");
            }
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to add all answers.", ex);
            throw new SaveDataExeption(ex.getMessage(), ex);
        }

        return (List<Answer>) answers;
    }
}
