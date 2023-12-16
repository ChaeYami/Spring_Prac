package hello.hellospring.domain;

// 회원 객체
public class Member {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { // 꺼낼 때
        return name;
    }

    public void setName(String name) { // 넣을 때
        this.name = name;
    }
}
