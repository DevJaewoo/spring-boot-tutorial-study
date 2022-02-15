package com.devjaewoo.hellospring.service;

import com.devjaewoo.hellospring.domain.Member;
import com.devjaewoo.hellospring.repository.MemberRepository;
import com.devjaewoo.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member result = memberService.findOne(saveId).orElse(null);
        assertThat(result.getName()).isEqualTo(member.getName());
    }

    @Test
    public void join_duplicated_exception() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring2");

        //when
        memberService.join(member1);
        memberService.join(member2);

        //then
        List<Member> result = memberService.findMembers();
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void findOne() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring2");

        //when
        Long id1 = memberService.join(member1);
        Long id2 = memberService.join(member2);

        //then
        Member result1 = memberService.findOne(id1).orElse(null);
        Member result2 = memberService.findOne(id2).orElse(null);

        assertThat(result1.getName()).isEqualTo(member1.getName());
        assertThat(result2.getName()).isEqualTo(member2.getName());
    }
}
