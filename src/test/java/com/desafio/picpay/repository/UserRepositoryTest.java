package com.desafio.picpay.repository;

import com.desafio.picpay.domain.user.User;
import com.desafio.picpay.domain.user.UserType;
import com.desafio.picpay.dto.UserDto;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get User successfully from DB")
    void findUserByDocumentCase1() {
        String document = "12345678998";
        UserDto userDto = new UserDto("Guilherme", "teste", document, new BigDecimal(10), "teste@gmail.com", "444", UserType.COMMON);

        this.createUser(userDto);

        Optional<User> result = this.userRepository.findUserByDocument(document);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get User from DB when user not exists")
    void findUserByDocumentCase2() {
        String document = "12345678990";

        Optional<User> result = this.userRepository.findUserByDocument(document);

        assertThat(result.isEmpty()).isTrue();
    }

    private User createUser(UserDto userDto) {
        User newUser = new User(userDto);
        this.entityManager.persist(newUser);

        return newUser;
    }
}