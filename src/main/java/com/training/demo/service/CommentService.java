package com.training.demo.service;

import com.training.demo.model.BlogPost;
import com.training.demo.model.Comment;
import com.training.demo.model.User;
import com.training.demo.repository.BlogPostRepository;
import com.training.demo.repository.CommentRepository;
import com.training.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private  CommentRepository commentRepository;

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private UserRepository userRepository;

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

        Optional<Comment> existingCommentOpt = commentRepository.findById(id);
        if (existingCommentOpt.isPresent()) {
            Comment existingComment = existingCommentOpt.get();
            existingComment.setContent(updatedComment.getContent());

            // Ensure the post and user are correctly set
            BlogPost post = blogPostRepository.findById(updatedComment.getPost().getId()).orElseThrow(() -> new RuntimeException("Post not found"));
            User user = userRepository.findById(updatedComment.getUser().getId()).orElseThrow(() -> new RuntimeException("User not found"));
            existingComment.setPost(post);
            existingComment.setUser(user);

            return commentRepository.save(existingComment);
        } else {
            throw new RuntimeException("Comment not found");
        }
    }


    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }
}

