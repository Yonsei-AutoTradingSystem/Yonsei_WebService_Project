package com.doubledragon.webservice.user.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterPostsRepositoryTest {

    @Autowired
    RegisterPostsRepository registerPostsRepository;

    @After
    public void cleanup() {
        /**
         이후 테스트 코드에 영향을 끼치지 않기 위해
         테스트 메소드가 끝날때 마다 respository 전체 비우는 코드
         **/
        registerPostsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        registerPostsRepository.save(RegisterPosts.builder()
                .userId("ljy1237")
                .userName("ljy")
                .password("1234")
                .accessKey("qwer")
                .secretKey("asdf")
                .build());

        //when
        List<RegisterPosts> registerPostsList = registerPostsRepository.findAll();

        //then
        RegisterPosts RegisterPosts = registerPostsList.get(0);
        assertThat(RegisterPosts.getUserId(), is("ljy1237"));
        assertThat(RegisterPosts.getPassword(), is("1234"));
    }
}
