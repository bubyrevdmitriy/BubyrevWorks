package com.example.demo.security;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.util.MultiValueMap;

import java.util.Map;
import java.util.Set;

public class RmeSessionChannelInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        //System.out.println("Channel Interceptor");

        /*MessageHeaders headers = message.getHeaders();
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        MultiValueMap<String, String> multiValueMap = headers.get(StompHeaderAccessor.NATIVE_HEADERS,MultiValueMap.class);

        Set<String> keys = multiValueMap.keySet();

        for (String key : keys) {
            //System.out.println("Key = " + key);
            //System.out.println("Values = " + multiValueMap.get(key));
        }*/

        return message;
    }
}
