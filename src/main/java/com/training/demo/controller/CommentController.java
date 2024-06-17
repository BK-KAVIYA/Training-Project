package com.training.demo.controller;

import com.training.demo.model.BlogPost;
import com.training.demo.model.Comment;
import com.training.demo.model.CommentDTO;
import com.training.demo.model.User;
import com.training.demo.service.BlogPostService;
import com.training.demo.service.CommentService;
import com.training.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    private BlogPostService blogPostService;

    @Autowired
    private UserService userService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable int id) {
        return commentService.getCommentById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable int id, @RequestBody Comment updatedComment) {
        if (updatedComment == null || updatedComment.getContent() == null) {
            return ResponseEntity.badRequest().build();
        }
        Comment updated = commentService.updateComment(id, updatedComment);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsForPost(@PathVariable Long postId, @RequestHeader("Authorization") String token) {
        List<Comment> comments = commentService.getCommentsForPost(postId, token);
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public Comment saveComment(@RequestBody CommentDTO commentDTO) {

        BlogPost post = blogPostService.getBlogPostById(Long.parseLong(commentDTO.getPost_id()));
        User user = userService.getUserById(Long.parseLong(commentDTO.getUser_id()));

        LocalDateTime currentTimeStamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentTimeStamp.format(formatter);

        // Map DTO to entity
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setPost(post);
        comment.setUser(user);
        comment.setCreatedAt(formattedDateTime);
        
        return commentService.saveComment(comment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable int id) {
        commentService.deleteComment(id);
    }
}
