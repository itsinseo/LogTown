package com.sarida.logtown.repository;

import com.sarida.logtown.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
