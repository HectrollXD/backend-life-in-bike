package mx.com.hexlink.es.lifeinbike.commondata.services;

import java.util.List;
import java.util.Optional;
import mx.com.hexlink.es.lifeinbike.commondata.exceptions.DatabaseExeption;



public interface IBasicServiceOperation <T, ID> {

    public T addNewRegister(T register) throws DatabaseExeption.SaveDataExeption;

    public List<T> getAllRegisters() throws DatabaseExeption.RetriveDataExeption;

    public Optional<T> findARegisterByID(ID id) throws DatabaseExeption.RetriveDataExeption;

    public T updateARegister(T register)  throws DatabaseExeption.SaveDataExeption;

    public void deleteARegisterByID(T register) throws DatabaseExeption.DeleteDataExeption;
}
