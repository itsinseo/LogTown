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
@IdClass(Follow.PK.class)
public class Follow {
	@Id
	@Column(name = "to_user", insertable = false, updatable = false)
	private String toUser; // 팔로우를 받는 User

	@Id
	@Column(name = "from_user", insertable = false, updatable = false)
	private String fromUser; // 팔로우 하는 User

	public Follow(String toUser, String fromUser) {
		this.toUser = toUser;
		this.fromUser = fromUser;
	}

	public static class PK implements Serializable {
		String toUser;
		String fromUser;
	}
}

