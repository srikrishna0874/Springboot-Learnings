package com.springboot.security_app.SecurityApplication.services;

import com.springboot.security_app.SecurityApplication.dto.PostDTO;
import com.springboot.security_app.SecurityApplication.entities.PostEntity;
import com.springboot.security_app.SecurityApplication.entities.User;
import com.springboot.security_app.SecurityApplication.exceptions.ResourceNotFoundException;
import com.springboot.security_app.SecurityApplication.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class))
                .toList();
    }


    @Override
    public PostDTO createNewPost(PostDTO postDTO) {
        PostEntity postEntity = modelMapper.map(postDTO, PostEntity.class);

        return modelMapper.map(postRepository.save(postEntity), PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long id) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info("Getting post by id: {}", id);

        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + id));

        return modelMapper.map(postEntity, PostDTO.class);
    }
}
