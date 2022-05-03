package kz.iitu.itse1909.amirlan.kernel.socket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WebSocketConfigTest {
    WebSocketConfig webSocketConfig = new WebSocketConfig();

    @Test
    void testConfigureMessageBroker() {
        Assertions.assertThrowsExactly(NullPointerException.class, () -> {
            webSocketConfig.configureMessageBroker(null);
        });
    }

    @Test
    void testRegisterStompEndpoints() {
        Assertions.assertThrowsExactly(NullPointerException.class, () -> {
            webSocketConfig.registerStompEndpoints(null);
        });
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme