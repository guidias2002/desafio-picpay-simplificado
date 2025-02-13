package com.desafio.picpay.service;

import com.desafio.picpay.domain.Transaction;
import com.desafio.picpay.domain.user.User;
import com.desafio.picpay.dto.TransactionDto;
import com.desafio.picpay.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AuthorizationService authorizationService;

    public Transaction createTransaction(TransactionDto transactionDto) throws Exception {
        User sender = userService.findUserById(transactionDto.senderId());
        User receiver = userService.findUserById(transactionDto.receiverId());

        userService.validateTransaction(sender, transactionDto.value());

        boolean isAuthorized = this.authorizationService.authorizeTransaction(sender, transactionDto.value());

        if(!isAuthorized) {
            throw new Exception("Não autorizado.");
        }

        Transaction newTransaction = new Transaction();

        newTransaction.setAmount(transactionDto.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionDto.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDto.value()));

        this.transactionRepository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        //notificationService.sendNotification(sender, "Transação realizada com sucesso");
        //notificationService.sendNotification(receiver, "Transação recebida com sucesso");

        return newTransaction;
    }


}
