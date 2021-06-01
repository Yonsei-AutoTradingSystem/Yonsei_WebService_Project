package com.doubledragon.webservice.user.domain.posts;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class RegisterPosts{

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 500, nullable = false)
    private String userName;

    private String userId;
    private String password;
    private String accessKey;
    private String secretKey;

    @Builder
    public RegisterPosts(String userId, String userName, String password, String accessKey, String secretKey) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }
}
