package kz.iitu.itse1909.amirlan.application.user.config;

import org.junit.jupiter.api.Test;

class JmsListenerTest {
    JmsListener jmsListener = new JmsListener();

    @Test
    void testListener() {
        jmsListener.listener("message");
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme