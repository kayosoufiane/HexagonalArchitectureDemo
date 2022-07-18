package com.indaco.hexademo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockPositionsController {
    @GetMapping("/stock-position-market-value/{symbol}")
    GetStockPositionAndMarketValueApiResponseDto getPositionAndMarketValue(@PathVariable String symbol) {
        return new GetStockPositionAndMarketValueApiResponseDto(symbol,
                // placeholders
                0, "", 0.0, 0.0
        );
    }

}
