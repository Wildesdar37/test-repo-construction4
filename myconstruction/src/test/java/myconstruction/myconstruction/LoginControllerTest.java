package myconstruction.myconstruction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import jakarta.transaction.Transactional;
import myconstruction.myconstruction.services.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class LoginControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserService userService;

  @BeforeEach
  void setUp() throws Exception {
    userService.createUser("bob", "bob@example.com", "example");
  }

  @Test
  void testLogin() throws Exception {
    this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk());
  }

  @Test
  void testPostLogin() throws Exception {

    this.mockMvc.perform(post("/login")
        .param("email", "bob@example.com")
        .param("password", "example")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
        .andDo(print())
        .andExpect(status().is3xxRedirection());
  }

}
