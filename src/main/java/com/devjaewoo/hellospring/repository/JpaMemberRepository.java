package com.devjaewoo.hellospring.repository;

import com.devjaewoo.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager entityManager;

    public JpaMemberRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Member save(Member member) {
        entityManager.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = entityManager.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        return entityManager.createQuery("SELECT m FROM Member m WHERE m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList()
                .stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return entityManager.createQuery("SELECT m FROM Member m", Member.class).getResultList();
    }
}
