package com.doubledragon.webservice.user.service;

import com.doubledragon.webservice.user.domain.posts.RegisterPostsRepository;
import com.doubledragon.webservice.user.dto.posts.RegisterPostsMainResponseDto;
import com.doubledragon.webservice.user.dto.posts.RegisterPostsSaveRequestDto;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RegisterPostsService {

    private RegisterPostsRepository registerPostsRepository;

    @Transactional
    public Long save(RegisterPostsSaveRequestDto dto){
        return registerPostsRepository.save(dto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<RegisterPostsMainResponseDto> findAllDesc() {
        return registerPostsRepository.findAllDesc()
                .map(RegisterPostsMainResponseDto::new)
                .collect(Collectors.toList());
    }
}
