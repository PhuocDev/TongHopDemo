package com.example.demopageable2.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository;

    public List<Blog> getAll() {
        return blogRepository.findAll();
    }
    public Blog addNewBlog(Blog blog) {
        //check blog here
        return blogRepository.save(blog);
    }
    public Blog updateBlog(Long id,Blog newBlog){
        Blog oldBlog = blogRepository.findById(id).get();
        oldBlog.setDescription(newBlog.getDescription());
        oldBlog.setTitle(newBlog.getTitle());
        blogRepository.saveAndFlush(oldBlog);

        return oldBlog;
    }
    public void deleteBlogById(Long id) throws Exception {
        if (blogRepository.existsById(id)){
            blogRepository.deleteById(id);
        } else throw new Exception("Blog id not found");
    }

    public Blog getBlogById(Long id) throws Exception {
        if (blogRepository.existsById(id)) {
            return blogRepository.findById(id).get();
        } else throw  new Exception("Not found blog id");
    }
}
