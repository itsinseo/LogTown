package com.sarida.logtown.repository;

import com.sarida.logtown.entity.Follow;
import com.sarida.logtown.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
	Optional<Follow> findByToUserAndFromUser(String toUsername, String fromUsername);

	@Query(value = "select a from Follow f INNER JOIN User a ON f.toUser = a.username where f.fromUser = :username")
	List<User> findAllByFromUser(@Param("username") String username);

	@Query(value = "select a from Follow f INNER JOIN User a ON f.fromUser = a.username where f.toUser = :username")
	List<User> findAllByToUser(@Param("username") String username);

	List<Follow> findByFromUser(String username);
}
