package edu.keepeasy.testcode;

import edu.keepeasy.testcode.domain.Message;
import edu.keepeasy.testcode.repo.MessageRepository;
import edu.keepeasy.testcode.repo.UserRepository;
import edu.keepeasy.testcode.service.Checker;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestCodeApplicationTests {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DataSource dataSource;
    @Autowired
    MessageRepository messageRepository;

    @Test
    @Order(1)
    public void validateLogin() {
        Assert.assertTrue(Checker.isUsernameValid("login"));
        Assert.assertFalse(Checker.isUsernameValid(""));
        Assert.assertFalse(Checker.isUsernameValid("gotta feeling"));
    }

    @Test
    @Order(2)
    public void validatePassword() {
        Assert.assertTrue(Checker.isPasswordValid("jcgfhgvjhbk8645311"));
        Assert.assertFalse(Checker.isPasswordValid("123456"));
        Assert.assertFalse(Checker.isPasswordValid("hello world"));
    }

    @Test
    @Order(3)
    public void checkConnection() {
        try {
            Connection connection = dataSource.getConnection();
            Assert.assertEquals("springtest", connection.getCatalog());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    @Order(4)
    public void insertToDB() {
        messageRepository.save(new Message("Кратко о том, зачем этот зверь нам нужен?",
                "QA/QC"));
        List<Message> byTag = messageRepository.findByTag("QA/QC");
        Assert.assertNotEquals(0, byTag.size());
        for (Message message : byTag) {
            Assert.assertEquals("Кратко о том, зачем этот зверь нам нужен?",
                    message.getText());
        }

    }


}
