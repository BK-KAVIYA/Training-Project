package com.training.demo.controller;

import com.training.demo.model.BlogPost;
import com.training.demo.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog-posts")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @GetMapping
    public List<BlogPost> getAllBlogPosts() {
        return blogPostService.getAllBlogPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable Long id) {
        BlogPost blogPost = blogPostService.getBlogPostById(id);
        if (blogPost != null) {
            return new ResponseEntity<>(blogPost, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<BlogPost> createBlogPost(@RequestBody BlogPost blogPost) {
        BlogPost createdBlogPost = blogPostService.createBlogPost(blogPost);
        return new ResponseEntity<>(createdBlogPost, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPost> updateBlogPost(@PathVariable Long id, @RequestBody BlogPost blogPost) {
        BlogPost updatedBlogPost = blogPostService.updateBlogPost(id, blogPost);
        if (updatedBlogPost != null) {
            return new ResponseEntity<>(updatedBlogPost, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlogPost(@PathVariable Long id) {
        blogPostService.deleteBlogPost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

