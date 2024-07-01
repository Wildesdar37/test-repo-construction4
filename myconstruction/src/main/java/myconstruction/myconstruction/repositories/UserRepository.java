package myconstruction.myconstruction.repositories;

import org.springframework.data.repository.CrudRepository;

import myconstruction.myconstruction.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

  User findByUsername(String username);
  User findByEmail(String email);
  
}
