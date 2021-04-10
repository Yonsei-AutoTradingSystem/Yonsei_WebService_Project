package com.doubledragon.webservice.dto.posts;

import com.doubledragon.webservice.domain.posts.Posts;

public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

}
