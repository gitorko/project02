package com.demo.project02.rserver.controller;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

import com.demo.project02.rcommon.GreetingRequest;
import com.demo.project02.rcommon.GreetingResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class GreetingController {

    /**
     * Fire-Forget - No response
     * Request-Response - Single value comes in, single value returned
     * Request-Stream - Single value comes in, multiple values returned
     * Channel - Multiple values comes in, multiple values returned.
     */
    @MessageMapping("greetings")
    Flux<GreetingResponse> greet(GreetingRequest greetingRequest) {
        var stream = Stream.generate(() -> new GreetingResponse("Hello " + greetingRequest.getName() + " @ " + Instant.now()));
        return Flux.fromStream(stream)
                .delayElements(Duration.ofSeconds(1));

    }
}
