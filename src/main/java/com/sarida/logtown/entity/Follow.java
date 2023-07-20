package com.sarida.logtown.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@Table(
		uniqueConstraints = @UniqueConstraint(columnNames = {"to_user", "from_user"})
)
public class Follow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "to_user")
	private String toUser; // 팔로우를 받는 User

	@Column(name = "from_user")
	private String fromUser; // 팔로우 하는 User

	public Follow(String toUser, String fromUser) {
		this.toUser = toUser;
		this.fromUser = fromUser;
	}
}

