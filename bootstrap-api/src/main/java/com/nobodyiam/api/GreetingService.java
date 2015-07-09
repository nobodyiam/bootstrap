package com.nobodyiam.api;

import com.nobodyiam.dto.Greeting;

import java.util.List;

/**
 * Created by Jason on 7/5/15.
 */
public interface GreetingService {
    /**
     * get greeting by id
     * @param greetingId
     * @return the greeting
     */
    Greeting getGreeting(long greetingId);

    /**
     * load greetings
     * @param limit
     * @param offset
     * @return greetings by limit and offset
     */
    List<Greeting> getGreetings(int limit, int offset);

    /**
     * add a new greeting
     * @param greeting
     * @return the number of rows inserted
     */
    int insertGreeting(Greeting greeting);

    /**
     * update a greeting
     * @param greeting
     * @return the number of rows updated
     */
    int updateGreeting(Greeting greeting);
}
