package com.example.glovodata.controller;

import com.example.glovodata.dto.UserDto;
import com.example.glovodata.model.entity.User;
import com.example.glovodata.servise.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    // handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }
    // handler method to handle login request
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    // handler method to handle list of users
    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }



    @PostMapping("/register/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String regist(@RequestParam String name,
                         @RequestParam String email,
                         @RequestParam String password){
        UserDto userDto = UserDto.builder()
                .firstName(name)
                .email(email)
                .password(password)
                .build();
        User user = userService.findUserByEmail(userDto.getEmail());
        if(user==null){
            userService.saveUser(userDto);
            return "/users";
        }else {
            return "redirect:/register?success";
        }
    }




}
