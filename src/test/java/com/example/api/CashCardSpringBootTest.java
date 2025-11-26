package com.example.api;

import com.example.api.cashcard.CashCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CashCardSpringBootTest {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnACashCardWhenDataIsSaved() {
        ResponseEntity<CashCard> response = restTemplate
                .withBasicAuth("user1","password1")
                .getForEntity("/cashcards/99", CashCard.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        CashCard cashCard = response.getBody();
        assertThat(cashCard).isNotNull();
        assertThat(cashCard.getId()).isEqualTo(99L);

    }
}
