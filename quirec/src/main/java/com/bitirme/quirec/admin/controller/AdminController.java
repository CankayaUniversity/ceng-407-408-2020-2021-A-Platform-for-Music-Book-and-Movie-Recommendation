package com.bitirme.quirec.admin.controller;

import com.bitirme.quirec.admin.service.AdminService;
import com.bitirme.quirec.recommendation.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/quirec-api/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Rating>> getRatings() {
        return new ResponseEntity<>(
                adminService.getRatings(),
                HttpStatus.OK
        );
    }

    @RequestMapping(path = "/databaseUpdate", method = RequestMethod.GET)
    public ResponseEntity<Void> databaseUpdate() {
        adminService.databaseUpdate();
        return new ResponseEntity<>(
                HttpStatus.OK
        );
    }

    //TODO: model retraining

}
