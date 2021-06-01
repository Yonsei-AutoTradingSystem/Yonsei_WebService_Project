package com.doubledragon.webservice.service;

import com.doubledragon.webservice.user.domain.posts.RegisterPostsRepository;
import com.doubledragon.webservice.user.dto.posts.RegisterPostsSaveRequestDto;
import com.doubledragon.webservice.domain.posts.PostsRepository;
import com.doubledragon.webservice.dto.posts.PostsMainResponseDto;
import com.doubledragon.webservice.dto.posts.PostsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PostsService {

    private PostsRepository postsRepository;
    private RegisterPostsRepository registerPostsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto dto){
        return postsRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public Long registerSave(RegisterPostsSaveRequestDto registerDto){
        return registerPostsRepository.save(registerDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<PostsMainResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .map(PostsMainResponseDto::new)
                .collect(Collectors.toList());
    }
}