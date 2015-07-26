package com.nobodyiam.web.controller;

import com.google.common.collect.Lists;
import com.nobodyiam.api.GreetingService;
import com.nobodyiam.dto.Greeting;
import com.nobodyiam.web.model.PageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by Jason on 7/5/15.
 */
@RestController
@RequestMapping("/greetings")
public class GreetingController {
    private final int TIME_OUT = 20; // 20 seconds timeout
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name = "executorService")
    private ExecutorService executorService;

    @Resource(name = "greetingService")
    private GreetingService greetingService;

    @RequestMapping(value = "/{greetingId}", method = RequestMethod.GET)
    public Greeting getGreeting(@PathVariable long greetingId) {
        return greetingService.getGreeting(greetingId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public PageModel<Greeting> getGreetings(@RequestParam(value = "limit", defaultValue = "10") final int limit,
                                            @RequestParam(value = "offset", defaultValue = "0") final int offset)
            throws Throwable {
        final PageModel<Greeting> result = new PageModel<Greeting>();

        Collection<Runnable> tasks = Lists.newArrayList();
        tasks.add(new Runnable() {
            @Override
            public void run() {
                result.setItems(greetingService.getGreetings(limit, offset));
            }
        });
        tasks.add(new Runnable() {
            @Override
            public void run() {
                result.setTotal(greetingService.countGreetings());
            }
        });

        List<Future> futures = Lists.newArrayList();
        for (Runnable task : tasks) {
            futures.add(executorService.submit(task));
        }

        try {
            for (Future f : futures) {
                f.get(TIME_OUT, TimeUnit.SECONDS); // wait for the task completed
            }
        } catch (ExecutionException e) {
            throw e.getCause(); // throw exceptions if task doesn't complete properly
        }

        return result;
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
