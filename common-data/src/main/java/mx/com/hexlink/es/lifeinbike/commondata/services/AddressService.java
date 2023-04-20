package mx.com.hexlink.es.lifeinbike.commondata.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.com.hexlink.es.lifeinbike.commondata.exceptions.DatabaseExeption.DeleteDataExeption;
import mx.com.hexlink.es.lifeinbike.commondata.exceptions.DatabaseExeption.RetriveDataExeption;
import mx.com.hexlink.es.lifeinbike.commondata.exceptions.DatabaseExeption.SaveDataExeption;
import mx.com.hexlink.es.lifeinbike.commondata.models.Address;
import mx.com.hexlink.es.lifeinbike.commondata.models.repositories.AddressRepository;



@Service
public class AddressService implements IBasicServiceOperation<Address, UUID>{
    private static final Logger LOG = LoggerFactory.getLogger(AddressService.class);

    @Autowired
    private AddressRepository addressRepository;



    @Override
    public Address addNewRegister(Address register) throws SaveDataExeption {
        LOG.info("Start service add new address.");

        Address address = null;

        try{
            address = addressRepository.save(register);

            if( address == null ){
                LOG.warn("The save method retrive a null object.");
            }
            else{
                LOG.info("The address was addedd successfully.");
            }
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to add new address.", ex);
            throw new SaveDataExeption(ex.getMessage(), ex);
        }

        return address;
    }


    @Override
    public List<Address> getAllRegisters() throws RetriveDataExeption {
        LOG.info("Start service get all addresses.");

        List<Address> address = null;

        try{
            address = addressRepository.findAll();
            LOG.info("All address has retrived successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to get all addresses.", ex);
            throw new RetriveDataExeption(ex.getMessage(), ex);
        }

        return address;
    }


    @Override
    public Optional<Address> findARegisterByID(UUID id) throws RetriveDataExeption {
        LOG.info("Start service get address by ID.");

        Optional<Address> address = null;

        try{
            address = addressRepository.findById(id);
            LOG.info("The address has retrived successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to get a address.", ex);
            throw new RetriveDataExeption(ex.getMessage(), ex);
        }

        return address;
    }


    @Override
    public Address updateARegister(Address register) throws SaveDataExeption {
        LOG.info("Start service update address.");

        Address address = null;

        try{
            register.setLastModifed(LocalDateTime.now());
            address = addressRepository.save(register);

            if( address == null ){
                LOG.warn("The update method retrive a null object.");
            }
            else{
                LOG.info("The address was update successfully.");
            }
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to update address.", ex);
            throw new SaveDataExeption(ex.getMessage(), ex);
        }

        return address;
    }


    @Override
    public void deleteARegisterByID(Address register) throws DeleteDataExeption {
        LOG.info("Start service delete address.");

        try{
            addressRepository.delete(register);
            LOG.info("The address was deleted successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to delete address.", ex);
            throw new DeleteDataExeption(ex.getMessage(), ex);
        }
    }
}
