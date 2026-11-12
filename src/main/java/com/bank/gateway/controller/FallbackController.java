package com.bank.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @RequestMapping("/customers")
    public Mono<ResponseEntity<Map<String, Object>>> customerFallback() {
        return buildFallbackResponse("Customer Service no disponible o tiempo de espera (2s) excedido.");
    }

    @RequestMapping("/accounts")
    public Mono<ResponseEntity<Map<String, Object>>> accountFallback() {
        return buildFallbackResponse("Account Service no disponible o tiempo de espera (2s) excedido.");
    }

    @RequestMapping("/credits")
    public Mono<ResponseEntity<Map<String, Object>>> creditFallback() {
        return buildFallbackResponse("Credit Service no disponible o tiempo de espera (2s) excedido.");
    }

    @RequestMapping("/transactions")
    public Mono<ResponseEntity<Map<String, Object>>> transactionFallback() {
        return buildFallbackResponse("Transaction Service no disponible o tiempo de espera (2s) excedido.");
    }

    private Mono<ResponseEntity<Map<String, Object>>> buildFallbackResponse(String message) {
        Map<String, Object> response = Map.of(
                "status", HttpStatus.SERVICE_UNAVAILABLE.value(),
                "error", "Service Unavailable / Gateway Timeout",
                "message", message,
                "timestamp", LocalDateTime.now().toString()
        );
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response));
    }
}
