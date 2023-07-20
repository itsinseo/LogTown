package com.sarida.logtown.repository;

import com.sarida.logtown.entity.Comment;
import com.sarida.logtown.entity.RecommentLike;
import com.sarida.logtown.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommentLikeRepository extends JpaRepository<RecommentLike, Long> {
    boolean existsByUserAndRecomment(User user, Comment recomment);

    Optional<RecommentLike> findByUserAndRecomment(User user, Comment recomment);
}
