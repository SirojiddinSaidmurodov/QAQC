package edu.keepeasy.testcode.repo;

import edu.keepeasy.testcode.domain.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {
}
