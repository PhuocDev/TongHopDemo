package com.example.demopageable2.blog;


import com.example.demopageable2.comments.Comment;
import com.example.demopageable2.comments.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    CommentService commentService;

    @GetMapping
    public List<Blog> index() {
        return blogService.getAll();
    }

    @GetMapping("/createDB")
    public List<Blog> createDB() {
        for (int i = 1; i < 50; i++) {
            Blog tempBlog = new Blog("Title " + i, "Description " + i);
            blogService.addNewBlog(tempBlog);
        }
        return blogService.getAll();
    }

    @PostMapping
    public Blog addNewBlog(@Valid @RequestBody Blog blog) {
        blogService.addNewBlog(blog);
        return blog;
    }

    @PutMapping("/{id}")
    public Blog updateBlog(@PathVariable(name = "id") Long id, @RequestBody Blog blog) throws Exception {
        blogService.updateBlog(id, blog);
        return blogService.getBlogById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable("id") Long id) throws Exception {
        blogService.deleteBlogById(id);
        return new ResponseEntity<String>("Da xoa blod id " + id, HttpStatus.OK);
    }

    @GetMapping("/cmtOf/{id}")
    public List<Comment> getCmt(@PathVariable("id") Long id) {
        return commentService.getAllCmtOfBlogId(id);
    }

    //Demo pageable
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllBlog(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        List<Blog> blogList = new ArrayList<>();
        Pageable paging = PageRequest.of(page, size);

        Page<Blog> blogPage;
        if (title == null) {
            blogPage = blogService.blogRepository.findAll(paging);
        } else {
            blogPage = blogService.blogRepository.findByTitle(title, paging);
        }
        blogList = blogPage.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("blogList", blogList);
        response.put("currentPage", blogPage.getNumber());
        response.put("totalItems", blogPage.getTotalElements());
        response.put("totalPages", blogPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
