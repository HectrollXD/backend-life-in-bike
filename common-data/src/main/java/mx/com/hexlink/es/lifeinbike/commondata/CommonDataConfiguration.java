package mx.com.hexlink.es.lifeinbike.commondata;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import mx.com.hexlink.es.lifeinbike.commondata.exceptions.DatabaseExeption.RetriveDataExeption;
import mx.com.hexlink.es.lifeinbike.commondata.exceptions.DatabaseExeption.SaveDataExeption;
import mx.com.hexlink.es.lifeinbike.commondata.models.SecurityQuestion;
import mx.com.hexlink.es.lifeinbike.commondata.services.SecurityQuestionService;



@Configuration
@AutoConfiguration
@ComponentScan("mx.com.hexlink.es.lifeinbike")
@EnableJpaRepositories
public class CommonDataConfiguration {
    private static final List<String> listOfQuestions = List.of(
        "APODO DE LA INFANCIA",
        "NOMBRE DE MI PRIMERA MASCOTA",
        "NOMBRE DE MI MEJOR AMIGO/A",
        "MI COMIDA FAVORITA",
        "MI COLOR FAVORITO"
    );

    @Autowired
    private SecurityQuestionService questionsService;


    @Bean
    public void initialQuestions() throws SaveDataExeption, RetriveDataExeption {
        List<SecurityQuestion> listOfSecurityQuestions = new ArrayList<>(
            questionsService.getAllRegisters()
        );
        Integer initialLength = listOfSecurityQuestions.size();

        if( initialLength < listOfQuestions.size() ){
            listOfQuestions.stream().forEach(str -> {
                Optional<SecurityQuestion> findingQuestion = listOfSecurityQuestions
                .stream().findFirst().filter(
                    question -> question.getQuestion().equals(str)
                );

                if (findingQuestion.isEmpty()) {
                    SecurityQuestion securityQuestion = new SecurityQuestion(str);
                    listOfSecurityQuestions.add(securityQuestion);
                }
                else{
                    listOfSecurityQuestions.remove(findingQuestion.get());
                }
            });

            questionsService.addMultipleRegisters(listOfSecurityQuestions);
        }
    }
}
