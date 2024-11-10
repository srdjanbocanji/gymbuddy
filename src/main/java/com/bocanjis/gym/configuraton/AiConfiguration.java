package com.bocanjis.gym.configuraton;

import io.netty.handler.logging.LogLevel;
import lombok.extern.log4j.Log4j2;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

@Configuration
@Log4j2
public class AiConfiguration {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.build();
    }

    @Bean
    @Scope("prototype")
    public WebClient.Builder webClientBuilder(ObjectProvider<WebClientCustomizer> customizerProvider) {
        WebClient.Builder builder = WebClient.builder();
        customizerProvider.orderedStream().forEach((customizer) -> customizer.customize(builder));
        builder.filter(logRequest());
        return builder;
    }

//    @Bean
//    public WebClient webClient(WebClient.Builder builder) {
//        return builder
//                .filter(logRequest())
//                .build();
//    }
//
    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Request: {} {} {}", clientRequest.method(), clientRequest.url(), clientRequest.body());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
            return Mono.just(clientRequest);
        });
    }
}
