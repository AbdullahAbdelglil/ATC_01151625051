package com.eventbooking.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/home-page")
    public String getHomePageEvents(Pageable pageable) {
        log.debug("REST request to get the home page");
        return "hello user";
    }
}
