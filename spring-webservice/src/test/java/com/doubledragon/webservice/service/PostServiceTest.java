package com.doubledragon.webservice.service;

import com.doubledragon.webservice.domain.posts.Posts;
import com.doubledragon.webservice.domain.posts.PostsRepository;
import com.doubledragon.webservice.dto.posts.PostsSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanup () {
        postsRepository.deleteAll();
    }

    @Test
    public void Dto데이터가_posts테이블에_저장된다 () {
        //given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .riseRate("0.1")
                .declineRate("0.2")
                .content("테스트")
                .coinName("테스트 타이틀")
                .build();

        //when
        postsService.save(dto);

        //then
        Posts posts = postsRepository.findAll().get(0);
        assertThat(posts.getRiseRate()).isEqualTo(dto.getRiseRate());
        assertThat(posts.getDeclineRate()).isEqualTo(dto.getDeclineRate());
        assertThat(posts.getContent()).isEqualTo(dto.getContent());
        assertThat(posts.getCoinName()).isEqualTo(dto.getCoinName());
    }
}