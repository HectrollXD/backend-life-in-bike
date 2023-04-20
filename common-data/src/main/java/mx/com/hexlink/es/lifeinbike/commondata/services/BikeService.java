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
import mx.com.hexlink.es.lifeinbike.commondata.models.Bike;
import mx.com.hexlink.es.lifeinbike.commondata.models.repositories.BikeRepository;



@Service
public class BikeService implements IBasicServiceOperation<Bike, UUID>{
    private static final Logger LOG = LoggerFactory.getLogger(BikeService.class);

    @Autowired
    private BikeRepository bikeRepository;



    @Override
    @Transactional
    public Bike addNewRegister(Bike register) throws SaveDataExeption {
        LOG.info("Start service add new bike.");

        Bike bike = null;

        try{
            bike = bikeRepository.save(register);

            if( bike == null ){
                LOG.warn("The save method retrive a null object.");
            }
            else{
                LOG.info("The bike was addedd successfully.");
            }
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to add new bike.", ex);
            throw new SaveDataExeption(ex.getMessage(), ex);
        }

        return bike;
    }

    
    @Override
    @Transactional(readOnly = true)
    public List<Bike> getAllRegisters() throws RetriveDataExeption {
        LOG.info("Start service get all bikes.");

        List<Bike> bike = null;

        try{
            bike = bikeRepository.findAll();
            LOG.info("All bikes has retrived successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to get all bikes.", ex);
            throw new RetriveDataExeption(ex.getMessage(), ex);
        }

        return bike;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Bike> findARegisterByID(UUID id) throws RetriveDataExeption {
        LOG.info("Start service get bike by ID.");

        Optional<Bike> bike = null;

        try{
            bike = bikeRepository.findById(id);
            LOG.info("The bike has retrived successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to get all bikes.", ex);
            throw new RetriveDataExeption(ex.getMessage(), ex);
        }

        return bike;
    }


    @Override
    @Transactional
    public Bike updateARegister(Bike register) throws SaveDataExeption {
        LOG.info("Start service update bike.");

        Bike bike = null;

        try{
            register.setLastModifed(LocalDateTime.now());
            bike = bikeRepository.save(register);

            if( bike == null ){
                LOG.warn("The update method retrive a null object.");
            }
            else{
                LOG.info("The bike was update successfully.");
            }
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to update bike.", ex);
            throw new SaveDataExeption(ex.getMessage(), ex);
        }

        return bike;
    }


    @Override
    @Transactional
    public void deleteARegisterByID(Bike register) throws DeleteDataExeption {
        LOG.info("Start service delete bike.");

        try{
            bikeRepository.delete(register);
            LOG.info("The bike was deleted successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to delete bike.", ex);
            throw new DeleteDataExeption(ex.getMessage(), ex);
        }
    }
}
