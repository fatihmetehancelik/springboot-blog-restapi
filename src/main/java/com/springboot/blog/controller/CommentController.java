package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @RequestBody CommentDto commentDto){

        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") Long postId ){
        return commentService.getCommentsByPostId(postId);

    }
    @GetMapping("/posts/{postsId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postsId") Long postId,
                                                     @PathVariable(value = "commentId") Long commentId){

        CommentDto commentDto = commentService.getCommentById(postId, commentId);

        return new ResponseEntity<>(commentDto,HttpStatus.OK);


    }
    @PutMapping("/posts/{postsId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment (@PathVariable(value ="postsId" ) Long postId,
                                                     @PathVariable(value ="id") Long commentId,
                                                     @RequestBody CommentDto commentDto){

        CommentDto updatedComment = commentService.updateComment(postId,commentId,commentDto);

        return new ResponseEntity<>(updatedComment,HttpStatus.OK);
        //postRepository.deleteById(post.getId());


    }

    /* @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){
        postService.deletePostById(id);

        return new ResponseEntity<>("Post Entity deleted Successfully",HttpStatus.OK);
    }*/

    @DeleteMapping("/posts/{postsId}/comments/{id}")
    public  ResponseEntity<String> deleteComment(@PathVariable(value = "postsId") long postId,
                                                 @PathVariable(value ="id" ) long commentId){

        commentService.deleteComment(postId,commentId);

        return new ResponseEntity<>("Deleted",HttpStatus.OK);


    }
}
