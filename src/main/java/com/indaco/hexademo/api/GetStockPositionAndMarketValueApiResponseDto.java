package com.indaco.hexademo.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetStockPositionAndMarketValueApiResponseDto {
    String symbol;
    Integer quantity;
    String currencyCode;
    Number cost;
    Number marketValue;
}
