package com.doubledragon.webservice.dto.posts;

import com.doubledragon.webservice.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String coinName;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String coinName, String content, String author) {
        this.coinName = coinName;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .coinName(coinName)
                .content(content)
                .author(author)
                .build();
    }

}
