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
    private String riseRate;
    private String declineRate;

    @Builder
    public PostsSaveRequestDto(String coinName, String content, String riseRate, String declineRate) {
        this.coinName = coinName;
        this.content = content;
        this.riseRate = riseRate;
        this.declineRate = declineRate;
    }

    public Posts toEntity(){
        return Posts.builder()
                .coinName(coinName)
                .content(content)
                .riseRate(riseRate)
                .declineRate(declineRate)
                .build();
    }
}
