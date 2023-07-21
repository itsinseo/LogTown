package com.sarida.logtown.repository;

import com.sarida.logtown.entity.Comment;
import com.sarida.logtown.entity.CommentLike;
import com.sarida.logtown.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Boolean existsByUserAndComment(User user, Comment comment);

    Optional<CommentLike> findByUserAndComment(User user, Comment comment);
}
