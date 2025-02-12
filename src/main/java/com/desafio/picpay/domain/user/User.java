package com.desafio.picpay.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "users")
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private String password;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType userType;

}
