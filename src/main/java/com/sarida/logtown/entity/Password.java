package com.sarida.logtown.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Getter
@Table(name = "password")
@NoArgsConstructor
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String password;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Password(String passwor정d) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }
}
