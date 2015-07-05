package com.nobodyiam.api;

import com.nobodyiam.dto.Greeting;

/**
 * Created by Jason on 7/5/15.
 */
public interface GreetingService {
    /**
     * get greeting by id
     * @param greetingId
     * @return
     */
    public Greeting getGreeting(int greetingId);
}
