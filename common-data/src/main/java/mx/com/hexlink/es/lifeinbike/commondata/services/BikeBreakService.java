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
import mx.com.hexlink.es.lifeinbike.commondata.models.BikeBreak;
import mx.com.hexlink.es.lifeinbike.commondata.models.repositories.BikeBreakRepository;



@Service
public class BikeBreakService implements IBasicServiceOperation<BikeBreak, UUID>{
    private static final Logger LOG = LoggerFactory.getLogger(BikeBreakService.class);

    @Autowired
    private BikeBreakRepository bikeBreaksRepository;



    @Override
    @Transactional
    public BikeBreak addNewRegister(BikeBreak register) throws SaveDataExeption {
        LOG.info("Start service add new bikeBreak.");

        BikeBreak bikeBreak = null;

        try{
            bikeBreak = bikeBreaksRepository.save(register);

            if( bikeBreak == null ){
                LOG.warn("The save method retrive a null object.");
            }
            else{
                LOG.info("The bike break was addedd successfully.");
            }
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to add new bike break.", ex);
            throw new SaveDataExeption(ex.getMessage(), ex);
        }

        return bikeBreak;
    }

    
    @Override
    @Transactional(readOnly = true)
    public List<BikeBreak> getAllRegisters() throws RetriveDataExeption {
        LOG.info("Start service get all bike breaks.");

        List<BikeBreak> bikeBreak = null;

        try{
            bikeBreak = bikeBreaksRepository.findAll();
            LOG.info("All bike breaks has retrived successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to get all bike breaks.", ex);
            throw new RetriveDataExeption(ex.getMessage(), ex);
        }

        return bikeBreak;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<BikeBreak> findARegisterByID(UUID id) throws RetriveDataExeption {
        LOG.info("Start service get bikeBreak by ID.");

        Optional<BikeBreak> bikeBreak = null;

        try{
            bikeBreak = bikeBreaksRepository.findById(id);
            LOG.info("The bike break has retrived successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to get all bike breaks.", ex);
            throw new RetriveDataExeption(ex.getMessage(), ex);
        }

        return bikeBreak;
    }


    @Override
    @Transactional
    public BikeBreak updateARegister(BikeBreak register) throws SaveDataExeption {
        LOG.info("Start service update bikeBreak.");

        BikeBreak bikeBreak = null;

        try{
            register.setLastModifed(LocalDateTime.now());
            bikeBreak = bikeBreaksRepository.save(register);

            if( bikeBreak == null ){
                LOG.warn("The update method retrive a null object.");
            }
            else{
                LOG.info("The bike break was update successfully.");
            }
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to update bike break.", ex);
            throw new SaveDataExeption(ex.getMessage(), ex);
        }

        return bikeBreak;
    }


    @Override
    @Transactional
    public void deleteARegisterByID(BikeBreak register) throws DeleteDataExeption {
        LOG.info("Start service delete bikeBreak.");

        try{
            bikeBreaksRepository.delete(register);
            LOG.info("The bike break was deleted successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to delete bike break.", ex);
            throw new DeleteDataExeption(ex.getMessage(), ex);
        }
    }
}
