package mx.com.hexlink.es.lifeinbike.apirest;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
import mx.com.hexlink.es.lifeinbike.commondata.exceptions.DatabaseExeption.RetriveDataExeption;
import mx.com.hexlink.es.lifeinbike.commondata.exceptions.DatabaseExeption.SaveDataExeption;
import mx.com.hexlink.es.lifeinbike.commondata.models.Account;
import mx.com.hexlink.es.lifeinbike.commondata.models.Address;
import mx.com.hexlink.es.lifeinbike.commondata.models.Answer;
import mx.com.hexlink.es.lifeinbike.commondata.models.Person;
import mx.com.hexlink.es.lifeinbike.commondata.models.SecurityQuestion;
import mx.com.hexlink.es.lifeinbike.commondata.services.AccountService;
import mx.com.hexlink.es.lifeinbike.commondata.services.AddressService;
import mx.com.hexlink.es.lifeinbike.commondata.services.AnswerService;
import mx.com.hexlink.es.lifeinbike.commondata.services.PersonService;
import mx.com.hexlink.es.lifeinbike.commondata.services.SecurityQuestionService;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;



//@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApirestApplicationTests {
	@Autowired
	private AccountService accountService;

	@Autowired
	private SecurityQuestionService securityQuestionService;

	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private PersonService personService;

	@Autowired
	private AddressService addressService;

	private static Account account;



	// Test to add new account
	@Test
	@Order(1)
	public void testToAddNewAccount() throws SaveDataExeption, RetriveDataExeption{
		Account accountToAdd = new Account();

		account = accountService.addNewRegister(accountToAdd);

		assertThat(account).isNotNull();

		List<SecurityQuestion> questions = securityQuestionService.getAllRegisters();

		assertThat(questions).isNotNull();
		assertThat(questions).isNotEmpty();

		List<Answer> answersToAdd = new ArrayList<>();

		answersToAdd.add(new Answer(questions.get(0), "pipo", account));
		answersToAdd.add(new Answer(questions.get(1), "pancho", account));
		answersToAdd.add(new Answer(questions.get(4), "chiles rellenos", account));

		List<Answer> answersAddedd = new ArrayList<>(
			answerService.addMultipleRegisters(answersToAdd)
		);

		assertThat(answersAddedd).isNotNull();
		assertThat(answersAddedd).isNotEmpty();
		assertTrue(answersAddedd.stream().anyMatch(answer -> answer.getAccount().equals(account)));
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
