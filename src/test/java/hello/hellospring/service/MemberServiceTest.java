package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
/*
    MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    */

    // memberservice에서 만든 객체 가져오기
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // 테스트 실행 전 각각 객체 생성
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); }

    // 테스트가 끝날 때 마다 데이터를 지워줘야 한다.
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }
    @Test
    void 회원가입() {

        //Given
        Member member = new Member();
        member.setName("Chaenii");

        //When
        Long saveId = memberService.join(member);

        //Then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("Chaenii");

        Member member2 = new Member();
        member2.setName("Chaenii");

        //when
        memberService.join(member1);

        /*
        try {
            memberService.join(member2);
            // 예외가 발생하지 않은 경우, 여기로 오게 됩니다.
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        }
        */

        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");


        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}