package mx.com.hexlink.es.lifeinbike.apirest;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import mx.com.hexlink.es.lifeinbike.commondata.exceptions.DatabaseExeption.SaveDataExeption;
import mx.com.hexlink.es.lifeinbike.commondata.models.Account;
import mx.com.hexlink.es.lifeinbike.commondata.models.Address;
import mx.com.hexlink.es.lifeinbike.commondata.models.Person;
import mx.com.hexlink.es.lifeinbike.commondata.services.AccountService;
import mx.com.hexlink.es.lifeinbike.commondata.services.AddressService;
import mx.com.hexlink.es.lifeinbike.commondata.services.PersonService;
import java.time.LocalDate;
import java.time.Month;
import static org.assertj.core.api.Assertions.*;



@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApirestApplicationTests {
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private PersonService personService;

	@Autowired
	private AddressService addressService;

	private static Account account;



	// Test to add new account
	@Test
	@Order(1)
	public void testToAddNewAccount() throws SaveDataExeption{
		Account accountToAdd = new Account();

		accountToAdd.setUsername("HectrollXD");
		accountToAdd.setEmail("hector.rmtnez89@gmail.com");
		accountToAdd.setPassword("faltaEncriptarContrasenia");

		account = accountService.addNewRegister(accountToAdd);

		assertThat(account).isNotNull();
		assertThat(account.getUsername()).isNotEmpty();
		assertThat(account.getEmail()).isNotEmpty();
		assertThat(account.getPassword()).isNotEmpty();
	}


	// Test to add person data
	@Test
	@Order(2)
	public void addPersonData() throws SaveDataExeption{
		assertThat(account).isNotNull();

		Person personToAdd = new Person();
		
		personToAdd.setNames("hector geovanny");
		personToAdd.setFirstLastname("rodriguez");
		personToAdd.setSecondLastname("martinez");
		personToAdd.setTelephone("3314580141");
		personToAdd.setAccount(account);
		personToAdd.setBirthDate(LocalDate.of(2000, Month.JUNE, 5));

		Person personAdded = personService.addNewRegister(personToAdd);

		assertThat(personAdded).isNotNull();
		assertThat(personAdded.getAccount()).isEqualTo(account);

		Address addressToAdd = new Address();

		addressToAdd.setStreetName("lopez santos");
		addressToAdd.setExtNumber("123");
		addressToAdd.setCity("el salto");
		addressToAdd.setPostalCode("45690");
		addressToAdd.setState("jalisco");
		addressToAdd.setAddresReferences("entre calle yucatan y jose garcia");
		addressToAdd.setPerson(personAdded);

		Address addressAdded = addressService.addNewRegister(addressToAdd);

		assertThat(addressAdded).isNotNull();
		assertThat(addressAdded.getPerson()).isEqualTo(personAdded);
	}
}
