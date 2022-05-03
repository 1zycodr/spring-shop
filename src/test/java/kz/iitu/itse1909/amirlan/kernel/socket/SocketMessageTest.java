package kz.iitu.itse1909.amirlan.kernel.socket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SocketMessageTest {
    SocketMessage socketMessage = new SocketMessage("from", "text", "time");

    @Test
    void testSetFrom() {
        socketMessage.setFrom("from");
    }

    @Test
    void testSetText() {
        socketMessage.setText("text");
    }

    @Test
    void testSetTime() {
        socketMessage.setTime("time");
    }

    @Test
    void testEquals() {
        boolean result = socketMessage.equals("o");
        Assertions.assertEquals(false, result);
    }

    @Test
    void testCanEqual() {
        boolean result = socketMessage.canEqual("other");
        Assertions.assertEquals(false, result);
    }

    @Test
    void testHashCode() {
        int result = socketMessage.hashCode();
        Assertions.assertNotNull(result);
    }

    @Test
    void testToString() {
        String result = socketMessage.toString();
        Assertions.assertNotNull(result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme