package myconstruction.myconstruction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;

import myconstruction.myconstruction.models.User;
import myconstruction.myconstruction.repositories.UserRepository;
import myconstruction.myconstruction.services.UserService;

@Controller
public class RegisterController {

  private final UserService userService;

  @Autowired
  public RegisterController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/register")
  public String showRegistrationForm() {
    return "register";
  }

  @PostMapping("/register")
  public String registerUser(@RequestParam("name") String name,
      @RequestParam("email") String email,
      @RequestParam("password") String password,
      Model model) {
    User user = this.userService.createUser(name,email,password);

    if (user == null) {
      model.addAttribute("error", "Has not been registered");
    }
  
    return "redirect:/login?registerSuccess";
  }

}
