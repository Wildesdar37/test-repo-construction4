4ppackage myconstruction.myconstruction;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class MyconstructionApplicationTests {

	public static void main(String[] args) {
		SpringApplication.run(MyconstructionApplication.class, args);
	}

	@Test
	void contextLoads() {
		// La prueba se pasa si el contexto se carga correctamente
	}

}
