package mx.com.hexlink.es.lifeinbike.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@EntityScan("mx.com.hexlink.es.lifeinbike")
@ComponentScan("mx.com.hexlink.es.lifeinbike")
public class ApirestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApirestApplication.class, args);
	}

}
