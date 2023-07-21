package com.sarida.logtown.repository;

import com.sarida.logtown.entity.Post;
import com.sarida.logtown.entity.PostLike;
import com.sarida.logtown.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    boolean existsByUserAndPost(User user, Post post);

    Optional<PostLike> findByUserAndPost(User user, Post post);
}
