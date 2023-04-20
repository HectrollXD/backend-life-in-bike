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
import mx.com.hexlink.es.lifeinbike.commondata.models.BikeSuspension;
import mx.com.hexlink.es.lifeinbike.commondata.models.repositories.BikeSuspensionRepository;



@Service
public class BikeSuspensionService implements IBasicServiceOperation<BikeSuspension, UUID>{
    private static final Logger LOG = LoggerFactory.getLogger(BikeSuspensionService.class);

    @Autowired
    private BikeSuspensionRepository bikeSuspensionRepository;



    @Override
    @Transactional
    public BikeSuspension addNewRegister(BikeSuspension register) throws SaveDataExeption {
        LOG.info("Start service add new bikeSuspension.");

        BikeSuspension bikeSuspension = null;

        try{
            bikeSuspension = bikeSuspensionRepository.save(register);

            if( bikeSuspension == null ){
                LOG.warn("The save method retrive a null object.");
            }
            else{
                LOG.info("The bike suspension was addedd successfully.");
            }
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to add new bike suspension.", ex);
            throw new SaveDataExeption(ex.getMessage(), ex);
        }

        return bikeSuspension;
    }

    
    @Override
    @Transactional(readOnly = true)
    public List<BikeSuspension> getAllRegisters() throws RetriveDataExeption {
        LOG.info("Start service get all bike suspensions.");

        List<BikeSuspension> bikeSuspension = null;

        try{
            bikeSuspension = bikeSuspensionRepository.findAll();
            LOG.info("All bike suspensions has retrived successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to get all bike suspensions.", ex);
            throw new RetriveDataExeption(ex.getMessage(), ex);
        }

        return bikeSuspension;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<BikeSuspension> findARegisterByID(UUID id) throws RetriveDataExeption {
        LOG.info("Start service get bikeSuspension by ID.");

        Optional<BikeSuspension> bikeSuspension = null;

        try{
            bikeSuspension = bikeSuspensionRepository.findById(id);
            LOG.info("The bike suspension has retrived successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to get all bike suspensions.", ex);
            throw new RetriveDataExeption(ex.getMessage(), ex);
        }

        return bikeSuspension;
    }


    @Override
    @Transactional
    public BikeSuspension updateARegister(BikeSuspension register) throws SaveDataExeption {
        LOG.info("Start service update bikeSuspension.");

        BikeSuspension bikeSuspension = null;

        try{
            register.setLastModifed(LocalDateTime.now());
            bikeSuspension = bikeSuspensionRepository.save(register);

            if( bikeSuspension == null ){
                LOG.warn("The update method retrive a null object.");
            }
            else{
                LOG.info("The bike suspension was update successfully.");
            }
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to update bike suspension.", ex);
            throw new SaveDataExeption(ex.getMessage(), ex);
        }

        return bikeSuspension;
    }


    @Override
    @Transactional
    public void deleteARegisterByID(BikeSuspension register) throws DeleteDataExeption {
        LOG.info("Start service delete bikeSuspension.");

        try{
            bikeSuspensionRepository.delete(register);
            LOG.info("The bike suspension was deleted successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to delete bike suspension.", ex);
            throw new DeleteDataExeption(ex.getMessage(), ex);
        }
    }
}
