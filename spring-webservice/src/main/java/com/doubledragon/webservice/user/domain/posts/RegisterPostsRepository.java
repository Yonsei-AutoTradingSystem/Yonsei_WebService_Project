package com.doubledragon.webservice.user.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.stream.Stream;

public interface RegisterPostsRepository extends JpaRepository<RegisterPosts, Long> {

    @Query("SELECT rp " +
            "FROM RegisterPosts rp " +
            "ORDER BY rp.id DESC")
    Stream<RegisterPosts> findAllDesc();


    @Query("SELECT rp " +
            "FROM RegisterPosts rp " +
            "WHERE rp.userId = :userid")
    RegisterPosts findByUserId(@Param("userid") String userId);
}