package com.devjaewoo.hellospring;

import com.devjaewoo.hellospring.repository.MemberRepository;
import com.devjaewoo.hellospring.repository.MemoryMemberRepository;
import com.devjaewoo.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
