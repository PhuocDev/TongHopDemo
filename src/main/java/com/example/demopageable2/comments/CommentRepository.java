package com.example.demopageable2.comments;

import com.example.demopageable2.blog.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query( value = "select * from comment c where c.blog_id = ?1",nativeQuery = true)
    List<Comment> findAllCommentByBlogId(Long blogId);
}
