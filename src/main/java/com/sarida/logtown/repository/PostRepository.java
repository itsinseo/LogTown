package com.sarida.logtown.repository;

import com.sarida.logtown.entity.Post;
import com.sarida.logtown.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {
    Slice<Post> findAllBy(Pageable pageable);

    Slice<Post> findAllByUser(User user, Pageable pageable);
}
