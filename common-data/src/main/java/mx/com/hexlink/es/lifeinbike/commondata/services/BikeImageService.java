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
import mx.com.hexlink.es.lifeinbike.commondata.models.BikeImage;
import mx.com.hexlink.es.lifeinbike.commondata.models.repositories.BikeImageRepository;



@Service
public class BikeImageService implements IBasicServiceOperation<BikeImage, UUID>{
    private static final Logger LOG = LoggerFactory.getLogger(BikeImageService.class);

    @Autowired
    private BikeImageRepository bikeImageRepository;



    @Override
    @Transactional
    public BikeImage addNewRegister(BikeImage register) throws SaveDataExeption {
        LOG.info("Start service add new bikeImages.");

        BikeImage bikeImages = null;

        try{
            bikeImages = bikeImageRepository.save(register);

            if( bikeImages == null ){
                LOG.warn("The save method retrive a null object.");
            }
            else{
                LOG.info("The bike image was addedd successfully.");
            }
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to add new bike images.", ex);
            throw new SaveDataExeption(ex.getMessage(), ex);
        }

        return bikeImages;
    }

    
    @Override
    @Transactional(readOnly = true)
    public List<BikeImage> getAllRegisters() throws RetriveDataExeption {
        LOG.info("Start service get all bike images.");

        List<BikeImage> bikeImages = null;

        try{
            bikeImages = bikeImageRepository.findAll();
            LOG.info("All bike images has retrived successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to get all bike images.", ex);
            throw new RetriveDataExeption(ex.getMessage(), ex);
        }

        return bikeImages;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<BikeImage> findARegisterByID(UUID id) throws RetriveDataExeption {
        LOG.info("Start service get bikeImages by ID.");

        Optional<BikeImage> bikeImages = null;

        try{
            bikeImages = bikeImageRepository.findById(id);
            LOG.info("The bike images has retrived successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to get all bike images.", ex);
            throw new RetriveDataExeption(ex.getMessage(), ex);
        }

        return bikeImages;
    }


    @Override
    @Transactional
    public BikeImage updateARegister(BikeImage register) throws SaveDataExeption {
        LOG.info("Start service update bikeImages.");

        BikeImage bikeImages = null;

        try{
            register.setLastModifed(LocalDateTime.now());
            bikeImages = bikeImageRepository.save(register);

            if( bikeImages == null ){
                LOG.warn("The update method retrive a null object.");
            }
            else{
                LOG.info("The bike image was update successfully.");
            }
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to update bike image.", ex);
            throw new SaveDataExeption(ex.getMessage(), ex);
        }

        return bikeImages;
    }


    @Override
    @Transactional
    public void deleteARegisterByID(BikeImage register) throws DeleteDataExeption {
        LOG.info("Start service delete bikeImages.");

        try{
            bikeImageRepository.delete(register);
            LOG.info("The bike image was deleted successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to delete bike image.", ex);
            throw new DeleteDataExeption(ex.getMessage(), ex);
        }
    }
}
