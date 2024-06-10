package com.training.demo.service;

import com.training.demo.model.BlogPost;

import java.util.List;

public interface BlogPostService {
    List<BlogPost> getAllBlogPosts();
    BlogPost getBlogPostById(Long id);
    BlogPost createBlogPost(BlogPost blogPost);
    BlogPost updateBlogPost(Long id, BlogPost blogPost);
    void deleteBlogPost(Long id);
}

