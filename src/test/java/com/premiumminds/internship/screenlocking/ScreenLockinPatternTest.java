package com.premiumminds.internship.screenlocking;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by aamado on 05-05-2022.
 */
@RunWith(JUnit4.class)
public class ScreenLockinPatternTest {

    /**
     * The corresponding implementations to test.
     * <p>
     * If you want, you can make others :)
     */
    public ScreenLockinPatternTest() {
    }


    @Test
    public void ScreenLockinPatternTestFirst3Length2Test() throws InterruptedException, ExecutionException, TimeoutException {
        Future<Integer> count = new ScreenLockinPattern().countPatternsFrom(3, 2);
        Integer result = count.get(10, TimeUnit.SECONDS);
        assertEquals(5, result.intValue());
    }

    @Test
    public void ScreenLockinPatternTestWrongSize() throws InterruptedException, ExecutionException, TimeoutException {
        Future<Integer> count = new ScreenLockinPattern().countPatternsFrom(5, 10);
        Integer result = count.get(10, TimeUnit.SECONDS);
        assertEquals(0, result.intValue());
    }

    @Test
    public void ScreenLockinPatternTestWrongPosition() throws InterruptedException, ExecutionException, TimeoutException {
        Future<Integer> count = new ScreenLockinPattern().countPatternsFrom(10, 5);
        Integer result = count.get(10, TimeUnit.SECONDS);
        assertEquals(0, result.intValue());
    }
}

