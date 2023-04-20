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
import mx.com.hexlink.es.lifeinbike.commondata.models.SingleBrandBike;
import mx.com.hexlink.es.lifeinbike.commondata.models.repositories.SingleBrandBikeRepository;



@Service
public class SingleBrandBikeService implements IBasicServiceOperation<SingleBrandBike, UUID>{
    private static final Logger LOG = LoggerFactory.getLogger(SingleBrandBikeService.class);

    @Autowired
    private SingleBrandBikeRepository singleBrandBikeRepository;



    @Override
    @Transactional
    public SingleBrandBike addNewRegister(SingleBrandBike register) throws SaveDataExeption {
        LOG.info("Start service add new singleBrandBike.");

        SingleBrandBike singleBrandBike = null;

        try{
            singleBrandBike = singleBrandBikeRepository.save(register);

            if( singleBrandBike == null ){
                LOG.warn("The save method retrive a null object.");
            }
            else{
                LOG.info("The single brand bike was addedd successfully.");
            }
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to add new single brand bike.", ex);
            throw new SaveDataExeption(ex.getMessage(), ex);
        }

        return singleBrandBike;
    }

    
    @Override
    @Transactional(readOnly = true)
    public List<SingleBrandBike> getAllRegisters() throws RetriveDataExeption {
        LOG.info("Start service get all single brand bikes.");

        List<SingleBrandBike> singleBrandBike = null;

        try{
            singleBrandBike = singleBrandBikeRepository.findAll();
            LOG.info("All single brand bikes has retrived successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to get all single brand bikes.", ex);
            throw new RetriveDataExeption(ex.getMessage(), ex);
        }

        return singleBrandBike;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SingleBrandBike> findARegisterByID(UUID id) throws RetriveDataExeption {
        LOG.info("Start service get singleBrandBike by ID.");

        Optional<SingleBrandBike> singleBrandBike = null;

        try{
            singleBrandBike = singleBrandBikeRepository.findById(id);
            LOG.info("The single brand bike has retrived successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to get all single brand bikes.", ex);
            throw new RetriveDataExeption(ex.getMessage(), ex);
        }

        return singleBrandBike;
    }


    @Override
    @Transactional
    public SingleBrandBike updateARegister(SingleBrandBike register) throws SaveDataExeption {
        LOG.info("Start service update singleBrandBike.");

        SingleBrandBike singleBrandBike = null;

        try{
            register.setLastModifed(LocalDateTime.now());
            singleBrandBike = singleBrandBikeRepository.save(register);

            if( singleBrandBike == null ){
                LOG.warn("The update method retrive a null object.");
            }
            else{
                LOG.info("The single brand bike was update successfully.");
            }
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to update single brand bike.", ex);
            throw new SaveDataExeption(ex.getMessage(), ex);
        }

        return singleBrandBike;
    }


    @Override
    @Transactional
    public void deleteARegisterByID(SingleBrandBike register) throws DeleteDataExeption {
        LOG.info("Start service delete singleBrandBike.");

        try{
            singleBrandBikeRepository.delete(register);
            LOG.info("The single brand bike was deleted successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to delete single brand bike.", ex);
            throw new DeleteDataExeption(ex.getMessage(), ex);
        }
    }
}
