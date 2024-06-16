package com.demo.project02.rclient;

import java.time.Duration;

import com.demo.project02.rcommon.GreetingRequest;
import com.demo.project02.rcommon.GreetingResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@SpringBootApplication
@Slf4j
public class RclientApp {

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(RclientApp.class, args);
        System.in.read();
    }

    @Bean
    public CommandLineRunner onStart() {
        return (args) -> {
            log.info("On Start!");
        };
    }

    @Bean
    Mono<RSocketRequester> rSocketRequester(RSocketRequester.Builder builder) {
        return builder
                .rsocketConnector(connector -> connector
                        .reconnect(Retry.fixedDelay(Integer.MAX_VALUE, Duration.ofSeconds(1))))
                .connectTcp("localhost", 8888);
    }

    @Bean
    ApplicationListener<ApplicationReadyEvent> client(Mono<RSocketRequester> client) {
        return (args) -> {
            var greetingResponseFlux = client.flatMapMany(rSocketRequester -> {
                return rSocketRequester.route("greetings")
                        .data(new GreetingRequest("Jack"))
                        .retrieveFlux(GreetingResponse.class);
            });
            greetingResponseFlux.subscribe(System.out::println);
        };
    }
}
