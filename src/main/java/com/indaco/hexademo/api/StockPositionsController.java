package com.indaco.hexademo.api;

import com.indaco.hexademo.domain.service.GetStockPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class StockPositionsController {

    @Autowired
    private GetStockPositionService getStockPositionService;

    @GetMapping("/stock-position-market-value/{symbol}")
    Mono<GetStockPositionAndMarketValueApiResponseDto> getPositionAndMarketValue(
            @PathVariable String symbol
    ) {
        return getStockPositionService.get(symbol).map(stockPosition -> new GetStockPositionAndMarketValueApiResponseDto(symbol,
                stockPosition.getQuantity(), stockPosition.getCurrencyCode(), stockPosition.getCost(),
                // placeholders
                0.0
            )
        );
    }

}
