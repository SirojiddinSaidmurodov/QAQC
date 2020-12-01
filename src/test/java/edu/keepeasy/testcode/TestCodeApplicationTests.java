package edu.keepeasy.testcode;

import edu.keepeasy.testcode.domain.Message;
import edu.keepeasy.testcode.domain.User;
import edu.keepeasy.testcode.repo.MessageRepository;
import edu.keepeasy.testcode.repo.UserRepository;
import edu.keepeasy.testcode.service.Checker;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestCodeApplicationTests {
    static Connection connection;
    @Autowired
    DataSource dataSource;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageRepository messageRepository;

    @BeforeAll
    public static void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Properties properties = new Properties();
            properties.load(new FileReader(new File("src/main/resources/application.properties")));
            connection = DriverManager.getConnection(
                    properties.getProperty("jdbc.test.url"),
                    properties.getProperty("spring.datasource.username"),
                    properties.getProperty("spring.datasource.password"));
            PreparedStatement set_foreign_0 = connection.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            PreparedStatement truncate_table_message = connection.prepareStatement("TRUNCATE TABLE message");
            PreparedStatement truncate_table_user = connection.prepareStatement("TRUNCATE TABLE user");
            PreparedStatement truncate_table_user_role = connection.prepareStatement("TRUNCATE TABLE user_role");
            PreparedStatement insertUser = connection.prepareStatement(
                    "INSERT user(id,active,password,username) value (1,1,'qwerty123456','sirojiddin')");
            PreparedStatement insertUserRole = connection.prepareStatement("INSERT user_role value (1,'USER')");
            PreparedStatement set_foreign_1 = connection.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
            set_foreign_0.executeUpdate();
            truncate_table_message.executeUpdate();
            truncate_table_user_role.executeUpdate();
            truncate_table_user.executeUpdate();
            insertUser.executeUpdate();
            insertUserRole.executeUpdate();
            set_foreign_1.executeUpdate();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | NoSuchMethodException |
                InvocationTargetException | InstantiationException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    @AfterAll
    public static void destruct() {
        try {
            PreparedStatement set_foreign_0 = connection.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            PreparedStatement truncate_table_message = connection.prepareStatement("TRUNCATE TABLE message");
            PreparedStatement truncate_table_user = connection.prepareStatement("TRUNCATE TABLE user");
            PreparedStatement truncate_table_user_role = connection.prepareStatement("TRUNCATE TABLE user_role");
            PreparedStatement set_foreign_1 = connection.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
            set_foreign_0.executeUpdate();
            truncate_table_message.executeUpdate();
            truncate_table_user.executeUpdate();
            truncate_table_user_role.executeUpdate();
            set_foreign_1.executeUpdate();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

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

    @Test
    @Order(5)
    public void isUserInDB() {
        User sirojiddin = userRepository.findByUsername("sirojiddin");
        Assert.assertNotNull(sirojiddin);
        Assert.assertEquals("qwerty123456", sirojiddin.getPassword());

        User notExists = userRepository.findByUsername("qwertyuiop");
        Assert.assertNull(notExists);
    }


}
