package com.doubledragon.webservice.user.dto.posts;


import com.doubledragon.webservice.user.domain.posts.RegisterPosts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterPostsSaveRequestDto {

    private String userId;
    private String userName;
    private String password;
    private String accessKey;
    private String secretKey;

    @Builder
    public RegisterPostsSaveRequestDto(String userId, String userName, String password, String accessKey, String secretKey) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public RegisterPosts toEntity(){
        return RegisterPosts.builder()
                .userId(userId)
                .userName(userName)
                .password(password)
                .accessKey(accessKey)
                .secretKey(secretKey)
                .build();
    }
}
