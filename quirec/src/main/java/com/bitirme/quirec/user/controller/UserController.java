package com.bitirme.quirec.user.controller;

import com.bitirme.quirec.user.model.LoginReturn;
import com.bitirme.quirec.user.model.User;
import com.bitirme.quirec.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/quirec-api/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> get(@PathVariable("id") long userId) {
        return new ResponseEntity<>(
                userService.get(userId),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<User> register(@RequestBody User user) throws Exception {
        return new ResponseEntity<>(
                userService.register(user),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginReturn> login(@RequestBody User user) throws Exception {
        return new ResponseEntity<>(
                userService.login(user),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> changePassword(@PathVariable("id") long userId, @RequestBody String passwords) throws Exception {
        return new ResponseEntity<>(
                userService.changePassword(userId, passwords),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    public ResponseEntity<Void> forgotPassword(@RequestBody User user) throws Exception {
        userService.forgotPassword(user);
        return new ResponseEntity<>(
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.PUT)
    public ResponseEntity<Void> resetPassword(@RequestBody User user) throws Exception {
        userService.resetPassword(user);
        return new ResponseEntity<>(
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") long userId) {
        userService.delete(userId);
        return new ResponseEntity<>(
                HttpStatus.OK
        );
    }
}