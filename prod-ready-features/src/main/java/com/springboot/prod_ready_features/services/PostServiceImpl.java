package com.springboot.prod_ready_features.services;

import com.springboot.prod_ready_features.dto.PostDTO;
import com.springboot.prod_ready_features.entities.PostEntity;
import com.springboot.prod_ready_features.exceptions.ResourceNotFoundException;
import com.springboot.prod_ready_features.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
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
        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + id));

        return modelMapper.map(postEntity, PostDTO.class);
    }
}
