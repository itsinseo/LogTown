package com.sarida.logtown.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
@EqualsAndHashCode
@DynamicInsert
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, unique = true)
	private String nickname;

	@Column(nullable = false)
	private String introduction;

	@Column(name = "role")
	@Enumerated(value = EnumType.STRING)
	private UserRoleEnum role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Password> passwordList = new LinkedList<>();

	public User(String username, String password, String nickname, String introduction, UserRoleEnum role) {
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.introduction = introduction;
		this.role = role;
	}

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}

	// 값을 입력하지 않는다면 default => "USER"
	@PrePersist
	public void prePersist(){
		this.role = this.role == null ? UserRoleEnum.valueOf("USER") : this.role;
	}
}
