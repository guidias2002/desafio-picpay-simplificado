package com.desafio.picpay.controller;

import com.desafio.picpay.domain.Transaction;
import com.desafio.picpay.dto.TransactionDto;
import com.desafio.picpay.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDto transactionDto) throws Exception {
        Transaction newTransaction = transactionService.createTransaction(transactionDto);

        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }
}
