package com.sarida.logtown.repository;

import com.sarida.logtown.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByParentCommentIsNullOrderByCreatedAtDesc();

    Comment findByParentCommentId(Long recommentId);
}
