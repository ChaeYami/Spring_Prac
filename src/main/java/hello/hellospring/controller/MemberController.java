package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    /*
    스프링 빈이 관리된다
    - 프로젝트가 실행되면 스프링이 Controller 애노테이션을 찾아서 해당 클래스를 들고 기다리는 상태가 된다.
     */
    private final MemberService memberService;

    /*
    생성자에 @Autowired 가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다.
    이렇게 객체 의존관계를 외부에서 넣어주는 것을 DI (Dependency Injection), 의존성 주입이라 한다.
    */
    @Autowired
    // Alt + Insert
    public MemberController(MemberService memberService) {

        this.memberService = memberService;
    }
}
