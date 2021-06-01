package com.doubledragon.webservice.web;

import com.doubledragon.webservice.user.dto.posts.RegisterPostsSaveRequestDto;
import com.doubledragon.webservice.user.service.RegisterPostsService;
import com.doubledragon.webservice.dto.posts.PostsSaveRequestDto;
import com.doubledragon.webservice.service.PostsService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WebRestController {

    private PostsService postsService;
    private RegisterPostsService registerPostsService;

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

    @PostMapping("/posts")
    public Long savePosts(@RequestBody PostsSaveRequestDto dto){
        return postsService.save(dto);
    }

    @PostMapping("/register")
    public Long registerSavePosts(@RequestBody RegisterPostsSaveRequestDto dto){
        return registerPostsService.save(dto);
    }
}