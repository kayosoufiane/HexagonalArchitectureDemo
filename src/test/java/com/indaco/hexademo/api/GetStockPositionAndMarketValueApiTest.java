package com.indaco.hexademo.api;

import com.github.javafaker.Faker;
import com.indaco.hexademo.domain.model.StockPosition;
import com.indaco.hexademo.domain.service.GetStockPositionService;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@WebFluxTest
public class GetStockPositionAndMarketValueApiTest {

    @Autowired
    private WebTestClient client;

    // Domain Service
    @MockBean
    private GetStockPositionService getStockPositionService;

    private static Faker faker = Faker.instance();

    @Test
    void get() {
        // arrange
        String symbol = "aapl";
        StockPosition fakeStockPosition = new StockPosition(
                symbol,
                BigDecimal.valueOf(faker.number().randomDouble(2,0,10000)),
                faker.currency().code(),
                BigDecimal.valueOf(faker.number().randomDouble(4,0,1000000))
        );
        when(getStockPositionService.get(symbol)).thenReturn(Mono.just(fakeStockPosition));
        // act
        client.get().uri("/stock-position-market-value/"+symbol)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
        //assert
                .expectStatus().isOk()
                .expectBody(GetStockPositionAndMarketValueApiResponseDto.class)
                .value(dto -> assertAll(
                        () -> assertThat(dto.getSymbol()).isEqualTo(symbol),
                        () -> assertThat(dto.getQuantity().doubleValue()).isCloseTo(fakeStockPosition.getQuantity().doubleValue(),Offset.offset(0.01)),
                        () -> assertThat(dto.getCurrencyCode()).isEqualTo(fakeStockPosition.getCurrencyCode()),
                        () -> assertThat(dto.getCost().doubleValue()).isCloseTo(fakeStockPosition.getCost().doubleValue(), Offset.offset(0.0001))
                        )
                );
    }
}
