package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") //웹 어플리케이션에서 /hello 일 때
    public String hello(Model model){ // MVC 할때 그 모델
        model.addAttribute("data","spring!!");
        return "hello"; //hello.html을 찾음
    }
}
