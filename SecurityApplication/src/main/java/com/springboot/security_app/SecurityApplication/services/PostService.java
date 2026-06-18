package com.springboot.security_app.SecurityApplication.services;

import com.springboot.security_app.SecurityApplication.dto.PostDTO;

import java.util.List;


public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO postDTO);

    PostDTO getPostById(Long id);
}
