package myconstruction.myconstruction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class RegisterControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate testRestTemplate;


  @Test
  void returnRegisterTemplate() throws Exception {
    assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/register", String.class))
        .contains("Register");
  }

  @Test
  void insertRegister() throws Exception {

    String url = "http://localhost:" + port + "/register";

    MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
    formData.add("carlos", "testuser");
    formData.add("email", "testuser@example.com");
    formData.add("password", "testpassword");

    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/x-www-form-urlencoded");
    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);

    ResponseEntity<String> response = testRestTemplate.postForEntity(url, request, String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
  }

}