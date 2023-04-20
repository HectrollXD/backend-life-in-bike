package mx.com.hexlink.es.lifeinbike.commondata.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import mx.com.hexlink.es.lifeinbike.commondata.exceptions.DatabaseExeption.*;
import mx.com.hexlink.es.lifeinbike.commondata.models.Account;
import mx.com.hexlink.es.lifeinbike.commondata.models.repositories.AccountRepository;



@Service
public class AccountService implements IBasicServiceOperation<Account, UUID>{
    private static final Logger LOG = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;



    @Override
    @Transactional
    public Account addNewRegister(Account register) throws SaveDataExeption {
        LOG.info("Start service add new account.");

        Account account = null;

        try{
            account = accountRepository.save(register);

            if( account == null ){
                LOG.warn("The save method retrive a null object.");
            }
            else{
                LOG.info("The account was addedd successfully.");
            }
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to add new account.", ex);
            throw new SaveDataExeption(ex.getMessage(), ex);
        }

        return account;
    }

    
    @Override
    @Transactional(readOnly = true)
    public List<Account> getAllRegisters() throws RetriveDataExeption {
        LOG.info("Start service get all accounts.");

        List<Account> account = null;

        try{
            account = accountRepository.findAll();
            LOG.info("All accounts has retrived successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to get all accounts.", ex);
            throw new RetriveDataExeption(ex.getMessage(), ex);
        }

        return account;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Account> findARegisterByID(UUID id) throws RetriveDataExeption {
        LOG.info("Start service get account by ID.");

        Optional<Account> account = null;

        try{
            account = accountRepository.findById(id);
            LOG.info("The account has retrived successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to get all accounts.", ex);
            throw new RetriveDataExeption(ex.getMessage(), ex);
        }

        return account;
    }


    @Override
    @Transactional
    public Account updateARegister(Account register) throws SaveDataExeption {
        LOG.info("Start service update account.");

        Account account = null;

        try{
            register.setLastModifed(LocalDateTime.now());
            account = accountRepository.save(register);

            if( account == null ){
                LOG.warn("The update method retrive a null object.");
            }
            else{
                LOG.info("The account was update successfully.");
            }
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to update account.", ex);
            throw new SaveDataExeption(ex.getMessage(), ex);
        }

        return account;
    }


    @Override
    @Transactional
    public void deleteARegisterByID(Account register) throws DeleteDataExeption {
        LOG.info("Start service delete account.");

        try{
            accountRepository.delete(register);
            LOG.info("The account was deleted successfully.");
        }
        catch(Exception ex){
            LOG.error("An error was ocurred while try to delete account.", ex);
            throw new DeleteDataExeption(ex.getMessage(), ex);
        }
    }
}
