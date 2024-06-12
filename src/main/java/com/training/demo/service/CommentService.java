package com.training.demo.service;

import com.training.demo.model.Comment;
import com.training.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public List<Comment> getCommentsForPost(Long postId, String token) {
        return  commentRepository.findByPostId(postId);
    }

    public Comment getCommentById(int id) {
        return commentRepository.findById(id).orElse(null);
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(int id, Comment updatedComment) {
        Comment existingComment = commentRepository.findById(id).orElse(null);
        if (existingComment != null) {
            // Update the existing comment properties
            existingComment.setContent(updatedComment.getContent());
            existingComment.setPost(updatedComment.getPost());
            existingComment.setUser(updatedComment.getUser());
            existingComment.setCreatedAt(updatedComment.getCreatedAt());
            // Save the updated comment
            return commentRepository.save(existingComment);
        } else {
            return null; // Or throw an exception, handle as needed
        }
    }

    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }
}

