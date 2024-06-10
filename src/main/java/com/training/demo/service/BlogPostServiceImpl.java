package com.training.demo.service;

import com.training.demo.model.BlogPost;
import com.training.demo.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Override
    public List<BlogPost> getAllBlogPosts() {
        return blogPostRepository.findAll();
    }

    @Override
    public BlogPost getBlogPostById(Long id) {
        return blogPostRepository.findById(id).orElse(null);
    }

    @Override
    public BlogPost createBlogPost(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    @Override
    public BlogPost updateBlogPost(Long id, BlogPost blogPost) {
        if (blogPostRepository.existsById(id)) {
            blogPost.setId(id);
            return blogPostRepository.save(blogPost);
        } else {
            return null; // Blog post with given id not found
        }
    }

    @Override
    public void deleteBlogPost(Long id) {
        blogPostRepository.deleteById(id);
    }
}

