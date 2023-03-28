package mx.com.hexlink.es.lifeinbike.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;



@SpringBootApplication
@EntityScan("mx.com.hexlink.es.lifeinbike")
public class ApirestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApirestApplication.class, args);
	}

}
