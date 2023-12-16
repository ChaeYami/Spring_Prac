package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트가 끝날 때 마다 데이터를 지워줘야 한다.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("Chaenii");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        /*
        // 파이썬 assert랑 같음. 참인지 아닌지
        Assertions.assertEquals(result,member); // jupiter assert, 둘이 같은지 확인
         */
        assertThat(member).isEqualTo(result); // assertj assert. alt+enter > static import

    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Chaenii1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Chaenii2");
        repository.save(member2);

        Member result = repository.findByName("Chaenii1").get();

        assertThat(member1).isEqualTo(result);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Chaenii1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Chaenii2");
        repository.save(member2);

        List<Member> result =  repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
