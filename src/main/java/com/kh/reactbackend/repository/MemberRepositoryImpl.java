package com.kh.reactbackend.repository;

import com.kh.reactbackend.dto.UsersDto;
import com.kh.reactbackend.entity.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Void createMember(Users users) {
        em.persist(users);
        return null;
    }

    @Override
    public Optional<Users> findOne(String memberId) {
        return Optional.ofNullable(em.find(Users.class, memberId));
    }


}
