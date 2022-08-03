package com.indaco.hexademo.domain.service;

import com.indaco.hexademo.domain.model.StockPosition;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetStockPositionService {
    public Mono<StockPosition> get(String symbol) {
        return Mono.empty();
    }
}
