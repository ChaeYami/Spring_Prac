package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") //Get 메서드, 웹 어플리케이션에서 /hello 일 때
    public String hello(Model model){ // MVC 할때 그 모델, hello라는 모델 생성
        // 모델에 hello!!이라는 데이터 넣기
        model.addAttribute("data","hello!!");
        return "hello"; //hello.html을 찾음
    }
    // 실행 : url/hello


    // 1. 템플릿 사용방식
    @GetMapping("hello-mvc")
    // 파라미터를 받아서 데이터 넘겨주기
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    } // url 뒤에 ?name=spring 으로 파라미터 넘겨주기
    // 실행 : http://localhost:8080/hello-mvc?name=spring
    // 결과 : 페이지에 hello spring


    // 2. API 방식 (걍 대충 문자로 어케 되는지 보면)
    @GetMapping("hello-string")
    @ResponseBody // viewResolver 사용하지 않고 http url body단에 문자 내용 반환
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }
    // 실행 : http://localhost:8080/hello-string?name=adfadsfdas
    // 결과 : 페이지에 hello adfadsfdas


    // 2-1. API 방식 제대로 써보기
    static class Hello { // HelloController.Hello 클래스
        private String name;
        // Alt + Insert : 디렉토리, 패키지, 클래스 등 생성 목록 보기
        // 에서 getter and setter : JavaBean 규약(property 접근방식)
        public String getName() { // 꺼낼 때
            return name;
        }

        public void setName(String name) { // 넣을 때
            this.name = name;
        }
    }
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi (@RequestParam("name") String name){
        Hello hello = new Hello(); // Hello 클래스로 hello 객체 만들기
        hello.setName(name); // Hello에 name 넣기 (setName)
        return hello; // 객체 넘기기
    }
    // 실행 : http://localhost:8080/hello-api?name=spring
    /*
    결과 :
        {
          "name": "spring"
        }
     */

}