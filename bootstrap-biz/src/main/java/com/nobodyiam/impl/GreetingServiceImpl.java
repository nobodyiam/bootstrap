package com.nobodyiam.impl;

import com.nobodyiam.api.GreetingService;
import com.nobodyiam.dto.Greeting;
import com.nobodyiam.mapper.GreetingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jason on 7/5/15.
 */
@Service("greetingService")
public class GreetingServiceImpl implements GreetingService {
    @Autowired
    private GreetingMapper greetingMapper;

    @Override
    public Greeting getGreeting(int greetingId) {
        return greetingMapper.getGreeting(greetingId);
    }
}