package com.doubledragon.webservice.web;

import com.doubledragon.webservice.service.PostsService;
import com.doubledragon.webservice.user.service.RegisterPostsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class WebController {

    private PostsService postsService;
    private RegisterPostsService registerPostsService;

    @GetMapping("/")
    public String main(Model model) {

        model.addAttribute("posts", postsService.findAllDesc());

        model.addAttribute("register", registerPostsService.findAllDesc());

        return "index";
    }
}