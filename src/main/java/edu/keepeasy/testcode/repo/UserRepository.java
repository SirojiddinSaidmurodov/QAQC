package edu.keepeasy.testcode.repo;

import edu.keepeasy.testcode.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
        User findByUsername(String name);
}
