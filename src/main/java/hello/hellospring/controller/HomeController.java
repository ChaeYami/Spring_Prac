package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 홈 컨트롤러
@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home"; // home.html 호출
    }
}

//참고: 컨트롤러가 정적 파일보다 우선순위가 높다
// - 그래서 '/' 경로일 때 index.html이 아니라 home.html이 뜨는 것임 (컨트롤러에 매핑되어있기 때문)
