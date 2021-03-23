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

    //profil sayfasında user bilgilerinin görülmesi için
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> get(@PathVariable("id") long userId) {
        return new ResponseEntity<>(
                userService.get(userId),
                HttpStatus.OK
        );
    }

    //register
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<User> register(@RequestBody User user) {
        return new ResponseEntity<>(
                userService.register(user),
                HttpStatus.OK
        );
    }

    //login
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginReturn> login(@RequestBody User user) {
        return new ResponseEntity<>(
                userService.login(user),
                HttpStatus.OK
        );
    }

    //change password
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> update(@PathVariable("id") long userId, @RequestBody User user) {
        return new ResponseEntity<>(
                userService.update(userId, user.getPassword()),
                HttpStatus.OK
        );
    }

    //delete user -> üyelik iptali gibi gerekirse kullanılır
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") long userId) {
        userService.delete(userId);
        return new ResponseEntity<>(
                HttpStatus.OK
        );
    }

    //forgot password
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> forgotPassword(@RequestBody User user) {
        userService.forgotPassword(user);
        return new ResponseEntity<>(
                HttpStatus.OK
        );
    }
}
