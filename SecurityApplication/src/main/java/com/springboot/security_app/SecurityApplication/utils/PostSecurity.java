package com.springboot.security_app.SecurityApplication.utils;

import com.springboot.security_app.SecurityApplication.dto.PostDTO;
import com.springboot.security_app.SecurityApplication.entities.PostEntity;
import com.springboot.security_app.SecurityApplication.entities.User;
import com.springboot.security_app.SecurityApplication.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostSecurity {

    private final PostService postService;

    public boolean isOwnerOfPost(Long postId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        PostDTO postDTO = postService.getPostById(postId);

        return postDTO.getAuthor().getId().equals(user.getId());


    }
}
