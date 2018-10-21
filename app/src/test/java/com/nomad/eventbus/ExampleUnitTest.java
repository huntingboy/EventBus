package com.nomad.eventbus;

import com.nomad.event.MyEvent;

import org.greenrobot.eventbus.EventBus;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testEvent() {
        MyEvent event = new MyEvent("msg from publisher main thread...");
        EventBus.getDefault().post(event);
    }
}