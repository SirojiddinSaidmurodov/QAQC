package edu.keepeasy.testcode.controller;

import edu.keepeasy.testcode.domain.Role;
import edu.keepeasy.testcode.domain.User;
import edu.keepeasy.testcode.repo.UserRepository;
import edu.keepeasy.testcode.service.Checker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class SignupController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        model.put("login_validation", "is-valid");
        model.put("password_validation", "is-valid");

        if (userFromDB != null) {
            model.put("login_validation", "is-invalid");
            model.put("login_invalid_message", "Пользователь с таким именем уже существует");
            return "/signup";
        }
        model.put("name", user.getUsername());
        model.put("pass", user.getPassword());
        boolean isValid = true;
        if (!Checker.isUsernameValid(user.getUsername())) {
            isValid = false;
            model.put("login_validation", "is-invalid");
            model.put("login_invalid_message", "Имя не может содержать пробелы или быть пустым");

        }
        if (!Checker.isPasswordValid(user.getPassword())) {
            isValid = false;
            model.put("password_validation", "is-invalid");
            model.put("password_invalid_message", "Пароль не может содержать пробелы иди быть короче 8 символов");
        }
        if (!isValid) {
            return "/signup";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }
}
