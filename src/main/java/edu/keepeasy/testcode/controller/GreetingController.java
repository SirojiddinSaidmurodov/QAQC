package edu.keepeasy.testcode.controller;

import edu.keepeasy.testcode.domain.Message;
import edu.keepeasy.testcode.repo.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private MessageRepository messageRepository;

    @PostMapping
    public String addMessage(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);
        messageRepository.save(message);
        model.put("messages", messageRepository.findAll());
        return "index";
    }

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        model.put("messages", messageRepository.findAll());
        return "index";
    }
}
