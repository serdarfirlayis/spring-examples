package com.serdarfirlayis.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimulationController {

    private final Counter orderCounter;

    // MeterRegistry Spring tarafından otomatik inject edilir
    public SimulationController(MeterRegistry registry) {
        this.orderCounter = Counter.builder("custom_order_created_total")
                .description("Toplam oluşturulan sipariş sayısı")
                .register(registry);
    }

    @GetMapping("/order")
    public String createOrder() {
        // Her istekte sayacı 1 artır
        orderCounter.increment();
        return "Sipariş oluşturuldu! Sayaç arttı.";
    }
}
