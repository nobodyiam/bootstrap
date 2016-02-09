package com.nobodyiam.impl;

import com.google.common.collect.Lists;
import com.nobodyiam.dto.Greeting;
import com.nobodyiam.mapper.GreetingMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Jason on 7/11/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class GreetingServiceImplTest {
    private GreetingServiceImpl greetingService;
    @Mock
    private GreetingMapper greetingMapper;
    private Greeting greeting;

    @Before
    public void setUp() {
        greetingService = new GreetingServiceImpl();
        //no need to explicitly mock objects when @Mock and MockitoJUnitRunner is used.
//        greetingMapper = mock(GreetingMapper.class);
        ReflectionTestUtils.setField(greetingService, "greetingMapper", greetingMapper);
        greeting = new Greeting();
    }

    @Test
    public void testGetGreeting() {
        long someGreetingId = 1;

        when(greetingMapper.getGreeting(someGreetingId)).thenReturn(greeting);

        assertEquals(greeting, greetingService.getGreeting(someGreetingId));
        verify(greetingMapper, times(1)).getGreeting(someGreetingId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetGreetingsWithInvalidLimit() {
        int invalidLimit = 0;
        int someOffset = 0;

        greetingService.getGreetings(invalidLimit, someOffset);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetGreetingsWithInvalidOffset() {
        int someLimit = 1;
        int someInvalidOffset = -1;

        greetingService.getGreetings(someLimit, someInvalidOffset);
    }

    @Test
    public void testGetGreetingsWithCorrectLimitAndOffset() {
        List<Greeting> greetings = Lists.newArrayList(greeting);
        int someCorrectLimit = 10;
        int someCorrectOffset = 10;

        when(greetingMapper.getGreetings(someCorrectLimit,someCorrectOffset)).thenReturn(greetings);

        assertEquals(greetings, greetingService.getGreetings(someCorrectLimit, someCorrectOffset));
        verify(greetingMapper, times(1)).getGreetings(someCorrectLimit, someCorrectOffset);
    }

    @Test(expected = NullPointerException.class)
    public void testInsertGreetingWithNullInput() {
        Greeting nullGreeting = null;

        greetingService.insertGreeting(nullGreeting);
    }

    @Test
    public void testInsertGreeting() {
        int affectedRows = 1;

        when(greetingMapper.insertGreeting(greeting)).thenReturn(affectedRows);

        assertEquals(affectedRows, greetingService.insertGreeting(greeting));
        verify(greetingMapper, times(1)).insertGreeting(greeting);
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateGreetingWithNullInput() {
        Greeting nullGreeting = null;

        greetingService.updateGreeting(nullGreeting);
    }

    @Test
    public void testUpdateGreeting() {
        int affectedRows = 1;

        when(greetingMapper.updateGreeting(greeting)).thenReturn(affectedRows);

        assertEquals(affectedRows, greetingService.updateGreeting(greeting));
        verify(greetingMapper, times(1)).updateGreeting(greeting);
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteGreetingWithNullInput() {
        Greeting nullGreeting = null;

        greetingService.deleteGreeting(nullGreeting);
    }

    @Test
    public void testDeleteGreeting() {
        int expectedDeletedRows = 1;

        when(greetingMapper.deleteGreeting(greeting)).thenReturn(expectedDeletedRows);

        assertEquals(expectedDeletedRows, greetingService.deleteGreeting(greeting));
        verify(greetingMapper, times(1)).deleteGreeting(greeting);
    }

    @Test
    public void testCountGreetings() {
        int someTotalNumber = 20;

        when(greetingMapper.countGreetings()).thenReturn(someTotalNumber);

        assertEquals(someTotalNumber, greetingService.countGreetings());
        verify(greetingMapper, times(1)).countGreetings();
    }
}
