package lt.viko.eif.gsiaudvytyte.TreciasDarbas1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TreciasDarbas1Application {

	public static void main(String[] args) {
		SpringApplication.run(TreciasDarbas1Application.class, args);

		System.out.println("SOAP link: http://localhost:8000/v3/api-docs");
		System.out.println("Swagger link: http://localhost:8000/swagger-ui/index.html");
	}

}
