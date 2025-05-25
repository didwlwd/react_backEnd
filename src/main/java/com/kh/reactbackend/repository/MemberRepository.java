package com.kh.reactbackend.repository;

import com.kh.reactbackend.dto.UsersDto;
import com.kh.reactbackend.entity.Users;

import java.util.Optional;

public interface MemberRepository {
    Void createMember(Users users);
    Optional<Users> findOne(String memberId);

}
