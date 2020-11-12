package com.kook1932.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
    // SpringDataJpa에서 제공하지 않는 메소드는 위처럼 쿼리로(@Query) 작성해도 된다.
}