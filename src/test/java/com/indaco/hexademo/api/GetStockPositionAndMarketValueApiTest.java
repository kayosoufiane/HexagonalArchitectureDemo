package com.indaco.hexademo.api;

import com.indaco.hexademo.domain.model.StockPosition;
import com.indaco.hexademo.domain.service.GetStockPositionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebFluxTest
public class GetStockPositionAndMarketValueApiTest {

    @Autowired
    private WebTestClient client;

    // Domain Service
    @MockBean
    private GetStockPositionService getStockPositionService;

    @Test
    void get() {
        // arrange
        String symbol = "aapl";
        StockPosition fakeStockPosition = new StockPosition();
        when(getStockPositionService.get(symbol)).thenReturn(fakeStockPosition);
        // act
        client.get().uri("/stock-position-market-value/"+symbol)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
        //assert
                .expectStatus().isOk()
                .expectBody(GetStockPositionAndMarketValueApiResponseDto.class)
                .value(dto -> assertThat(dto.getSymbol()).isEqualTo(symbol));
    }
}
