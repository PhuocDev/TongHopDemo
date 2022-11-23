package com.example.demopageable2.comments;

import com.example.demopageable2.blog.Blog;
import com.example.demopageable2.blog.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cmt")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    BlogService blogService;

    @GetMapping
    public List<Comment> getAll() {
        return commentService.getAll();
    }
    @GetMapping("/createDB")
    public List<Comment> createDB(){
        for (int i = 1; i < 5 ; i++) {
            Comment comment = new Comment("Content " + 100 + i);
            Blog newBlod = blogService.getAll().get(i);
            comment.setBlog(newBlod);
            commentService.save(comment);
        }
        return commentService.getAll();
    }
    @GetMapping("/{blogId}/add")
    public ResponseEntity<Comment> addNewComment(@PathVariable("blogId") Long blogId, @Valid @RequestBody Comment comment) throws Exception {
        Comment newCmt = new Comment();
        newCmt.setContent(comment.getContent());
        commentService.addNewComment(blogId,newCmt);
        return new ResponseEntity<Comment>(commentService.getCommendById(newCmt.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/{cmtId}")
    public ResponseEntity<?> deleteCMT( @PathVariable("cmtId") Long cmtId) {
        commentService.deleteCmt(cmtId);
        return new ResponseEntity<String>("Deleted cmt id " + cmtId, HttpStatus.OK);
    }
}
