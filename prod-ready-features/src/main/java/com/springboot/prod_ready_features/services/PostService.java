package com.springboot.prod_ready_features.services;


import com.springboot.prod_ready_features.dto.PostDTO;

import java.util.List;


public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO postDTO);

    PostDTO getPostById(Long id);
}
