package com.desafio.picpay.service;

import com.desafio.picpay.domain.user.User;
import com.desafio.picpay.domain.user.UserType;
import com.desafio.picpay.dto.UserDto;
import com.desafio.picpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Usuário do tipo lojista não está autorizado a realizar tranferências.");
        }

        if(sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente.");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado."));
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    public User createUser(UserDto userDto) {
        User newUser = new User(userDto);
        this.saveUser(newUser);

        return newUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
