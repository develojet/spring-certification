package com.example.api.cashcard;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
interface CashCardRepository extends CrudRepository<CashCard, Long> {

}
