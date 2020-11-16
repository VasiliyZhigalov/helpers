package com.lastfeofan.helpers;

import com.lastfeofan.helpers.domain.Usr;
import com.lastfeofan.helpers.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private final UserRepository userRepository;

    public GreetingController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World")
                                   String name, Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Usr> users = userRepository.findAll();
        model.put("users", users);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String name, @RequestParam String email, Map<String, Object> model) {
        userRepository.save(new Usr(name, email));
        Iterable<Usr> users = userRepository.findAll();
        model.put("users", users);
        return "main";
    }

    @PostMapping("main/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Usr> users;
        if (filter != null || filter.isEmpty()) {
          users =  userRepository.findByName(filter);
        } else {
            users = userRepository.findAll();
        }
            model.put("users", users);
            return "main";
        }

    }
