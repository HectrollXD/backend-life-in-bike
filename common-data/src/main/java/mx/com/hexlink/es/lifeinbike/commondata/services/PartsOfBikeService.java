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
import mx.com.hexlink.es.lifeinbike.commondata.models.PartsOfBike;
import mx.com.hexlink.es.lifeinbike.commondata.models.repositories.PartsOfBikeRepository;



@Service
public class PartsOfBikeService implements IBasicServiceOperation<PartsOfBike, UUID>{
    private static final Logger LOG = LoggerFactory.getLogger(PartsOfBikeService.class);

    @Autowired
    private PartsOfBikeRepository partsOfBikeRepository;



    @Override
    @Transactional
    public PartsOfBike addNewRegister(PartsOfBike register) throws SaveDataExeption {
        LOG.info("Start service add new partsOfBike.");

        PartsOfBike partsOfBike = null;

        try{
            partsOfBike = partsOfBikeRepository.save(register);

            if( partsOfBike == null ){
                LOG.warn("The save method retrive a null object.");
            }
            else{
                LOG.info("The part of bike was addedd successfully.");
            }
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to add new part of bike.", ex);
            throw new SaveDataExeption(ex.getMessage(), ex);
        }

        return partsOfBike;
    }

    
    @Override
    @Transactional(readOnly = true)
    public List<PartsOfBike> getAllRegisters() throws RetriveDataExeption {
        LOG.info("Start service get all parts of bike.");

        List<PartsOfBike> partsOfBike = null;

        try{
            partsOfBike = partsOfBikeRepository.findAll();
            LOG.info("All parts of bike has retrived successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to get all parts of bike.", ex);
            throw new RetriveDataExeption(ex.getMessage(), ex);
        }

        return partsOfBike;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PartsOfBike> findARegisterByID(UUID id) throws RetriveDataExeption {
        LOG.info("Start service get partsOfBike by ID.");

        Optional<PartsOfBike> partsOfBike = null;

        try{
            partsOfBike = partsOfBikeRepository.findById(id);
            LOG.info("The part of bike has retrived successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to get all parts of bike.", ex);
            throw new RetriveDataExeption(ex.getMessage(), ex);
        }

        return partsOfBike;
    }


    @Override
    @Transactional
    public PartsOfBike updateARegister(PartsOfBike register) throws SaveDataExeption {
        LOG.info("Start service update partsOfBike.");

        PartsOfBike partsOfBike = null;

        try{
            register.setLastModifed(LocalDateTime.now());
            partsOfBike = partsOfBikeRepository.save(register);

            if( partsOfBike == null ){
                LOG.warn("The update method retrive a null object.");
            }
            else{
                LOG.info("The part of bike was update successfully.");
            }
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to update part of bike.", ex);
            throw new SaveDataExeption(ex.getMessage(), ex);
        }

        return partsOfBike;
    }


    @Override
    @Transactional
    public void deleteARegisterByID(PartsOfBike register) throws DeleteDataExeption {
        LOG.info("Start service delete partsOfBike.");

        try{
            partsOfBikeRepository.delete(register);
            LOG.info("The part of bike was deleted successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to delete part of bike.", ex);
            throw new DeleteDataExeption(ex.getMessage(), ex);
        }
    }
}
