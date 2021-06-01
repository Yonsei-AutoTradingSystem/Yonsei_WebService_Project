package com.doubledragon.webservice.user.dto.posts;

import com.doubledragon.webservice.user.domain.posts.RegisterPosts;
import lombok.Getter;

@Getter
public class RegisterPostsMainResponseDto {

    private Long id;
    private String userId;
    private String userName;
    private String password;
    private String accessKey;
    private String secretKey;

    public RegisterPostsMainResponseDto(RegisterPosts entity) {
        id = entity.getId();
        userId = entity.getUserId();
        userName = entity.getUserName();
        password = entity.getPassword();
        accessKey = entity.getAccessKey();
        secretKey = entity.getSecretKey();
    }
}