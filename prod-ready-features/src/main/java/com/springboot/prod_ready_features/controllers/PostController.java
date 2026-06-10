package com.springboot.prod_ready_features.controllers;

import com.springboot.prod_ready_features.dto.PostDTO;
import com.springboot.prod_ready_features.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping(path = "/{postId}")
    public PostDTO getPostById(@PathVariable(name = "postId") Long id) {
        return postService.getPostById(id);
    }

    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO postDTO) {
        return postService.createNewPost(postDTO);
    }

    @PutMapping(path = "/{postId}")
    public PostDTO updatePost(@PathVariable(name = "postId") Long id, @RequestBody PostDTO postDTO) {
        return postService.updatePost(id, postDTO);
    }


}
