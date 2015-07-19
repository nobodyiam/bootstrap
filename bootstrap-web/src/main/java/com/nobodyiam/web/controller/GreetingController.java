package com.nobodyiam.web.controller;

import com.nobodyiam.api.GreetingService;
import com.nobodyiam.dto.Greeting;
import com.nobodyiam.web.model.PageModel;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by Jason on 7/5/15.
 */
@RestController
@RequestMapping("/greetings")
public class GreetingController {
    @Resource(name = "greetingService")
    private GreetingService greetingService;

    @RequestMapping(value = "/{greetingId}", method = RequestMethod.GET)
    public Greeting getGreeting(@PathVariable long greetingId) {
        return greetingService.getGreeting(greetingId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public PageModel<Greeting> getGreetings(@RequestParam(value = "limit", defaultValue = "10") int limit,
                                       @RequestParam(value = "offset", defaultValue = "0") int offset) {
        List<Greeting> greetings = greetingService.getGreetings(limit, offset);

        return new PageModel<Greeting>(greetingService.countGreetings(), greetings);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Greeting addGreeting(@RequestBody Greeting greeting) {
        greetingService.insertGreeting(greeting);
        return greetingService.getGreeting(greeting.getId());
    }

    @RequestMapping(value = "/{greetingId}", method = RequestMethod.PUT)
    public Greeting updateGreeting(@PathVariable long greetingId, @RequestBody Greeting greeting) {
        greeting.setId(greetingId);
        greetingService.updateGreeting(greeting);
        return greetingService.getGreeting(greetingId);
    }

    @RequestMapping(value = "/{greetingId}", method = RequestMethod.DELETE)
    public Greeting deleteGreeting(@PathVariable long greetingId) {
        Greeting greeting = greetingService.getGreeting(greetingId);
        checkArgument(greeting != null, String.format("Greeting (id = %d) doesn't exist!", greetingId));

        greetingService.deleteGreeting(greeting);
        return greeting;
    }
}
