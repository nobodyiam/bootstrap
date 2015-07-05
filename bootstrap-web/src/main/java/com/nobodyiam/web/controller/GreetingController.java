package com.nobodyiam.web.controller;

import com.nobodyiam.api.GreetingService;
import com.nobodyiam.dto.Greeting;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Jason on 7/5/15.
 */
@RestController
@RequestMapping("/greeting")
public class GreetingController {
    @Resource(name = "greetingService")
    private GreetingService greetingService;

    @RequestMapping(value = "/{greetingId}", method = RequestMethod.GET)
    public Greeting getGreeting(@PathVariable int greetingId) {
        return greetingService.getGreeting(greetingId);
    }
}
