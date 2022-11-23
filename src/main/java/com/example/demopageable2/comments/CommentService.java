package com.example.demopageable2.comments;

import com.example.demopageable2.blog.Blog;
import com.example.demopageable2.blog.BlogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    BlogService blogService;

    public List<Comment> getAll() {
        return commentRepository.findAll();
    }
    public Comment getCommendById(Long id) {
        if (commentRepository.existsById(id))
            return commentRepository.findById(id).get();
        else return null;
    }
    public Comment addNewComment(Long blogId, Comment newComment) throws Exception {
        Blog blogForNewComment = blogService.getBlogById(blogId);
        newComment.setBlog(blogForNewComment);
        commentRepository.save(newComment);
        return newComment;
    }
    public Comment updateCmt(Long id, Comment newComment){
        Comment oldComment = commentRepository.findById(id).get();
        oldComment.setBlog(newComment.getBlog());
        oldComment.setContent(newComment.getContent());
        commentRepository.saveAndFlush(oldComment);

        return oldComment;
    }

    public void deleteCmt(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        }
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    public List<Comment> getAllCmtOfBlogId(Long id) {
        return commentRepository.findAllCommentByBlogId(id);
    }
}
