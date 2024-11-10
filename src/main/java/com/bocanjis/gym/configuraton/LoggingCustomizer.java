package com.bocanjis.gym.configuraton;

import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.client.reactive.ClientHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Log4j2
@Component
public class LoggingCustomizer implements WebClientCustomizer {

    @Override public void customize(WebClient.Builder webClientBuilder) {
        webClientBuilder.filter((request, next) -> {
            logRequest(request);
            return next
                    .exchange(interceptBody(request))
                    .doOnNext(this::logResponse)
                    .map(this::interceptBody);
        });
    }

    private ClientRequest interceptBody(ClientRequest request) {
        return ClientRequest.from(request)
                .body((outputMessage, context) -> request.body().insert(new ClientHttpRequestDecorator(outputMessage) {
                    @Override public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                        return super.writeWith(Mono.from(body)
                                .doOnNext(dataBuffer -> logRequestBody(dataBuffer)));
                    }
                }, context))
                .build();
    }

    private ClientResponse interceptBody(ClientResponse response) {
        return response.mutate()
                .body(data -> data.doOnNext(this::logResponseBody))
                .build();
    }

    private void logRequest(ClientRequest request) {
        log.info("DOWNSTREAM REQUEST: METHOD {}, URI: {}, HEADERS: {}", request.method(), request.url(), request.headers());
    }

    private void logRequestBody(DataBuffer dataBuffer) {
        log.info("DOWNSTREAM REQUEST: BODY: {}", dataBuffer.toString(StandardCharsets.UTF_8));
    }

    private void logResponse(ClientResponse response) {
        log.info("DOWNSTREAM RESPONSE: STATUS: {}, HEADERS: {}", response.rawStatusCode(), response.headers().asHttpHeaders());
    }

    private void logResponseBody(DataBuffer dataBuffer) {
        log.info("DOWNSTREAM RESPONSE: BODY: {}", dataBuffer.toString(StandardCharsets.UTF_8));
    }

}
