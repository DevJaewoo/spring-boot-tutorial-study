package com.devjaewoo.hellospring;

import com.devjaewoo.hellospring.repository.JdbcTemplateMemberRepository;
import com.devjaewoo.hellospring.repository.JpaMemberRepository;
import com.devjaewoo.hellospring.repository.MemberRepository;
import com.devjaewoo.hellospring.repository.MemoryMemberRepository;
import com.devjaewoo.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

}
