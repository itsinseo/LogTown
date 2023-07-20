package com.sarida.logtown.repository;

import com.sarida.logtown.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
	Optional<Follow> findByToUserAndFromUser(String toUsername, String fromUsername);
}
