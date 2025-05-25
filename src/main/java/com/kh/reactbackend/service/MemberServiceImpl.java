package com.kh.reactbackend.service;

import com.kh.reactbackend.dto.UsersDto;
import com.kh.reactbackend.entity.Users;
import com.kh.reactbackend.enums.CommonEnums;
import com.kh.reactbackend.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public Void createMember(UsersDto.Create createDto) {
        Users users = createDto.toEntity();
        return memberRepository.createMember(users);
    }

    @Override
    public UsersDto.Response login(UsersDto.Login loginDto) {
        Users users = loginDto.toDto();
        UsersDto.Response user = memberRepository.findOne(users.getMemberId())
                .map(UsersDto.Response::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 회원입니다."));

        UsersDto.Response password = memberRepository.findOne(users.getMemberId())
                .map(UsersDto.Response::toPassword)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호를 찾을 수 없습니다."));

        if(!users.getPassword().equals(password.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        if(user.getStatus().equals(CommonEnums.Status.N)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "탈퇴된 회원입니다.");
        }
        return user;
    }

    @Override
    public UsersDto.Response selectMember(String id) {
        return memberRepository.findOne(id)
                .map(UsersDto.Response::toDto)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    @Override
    public UsersDto.Response update(UsersDto.Update updateDto) {
        Users user = memberRepository.findOne(updateDto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 회원입니다."));

        user.updateMemberInfo(
                updateDto.getId(),
                updateDto.getUser_nikname(),
                updateDto.getUser_name(),
                updateDto.getEmail(),
                updateDto.getUser_thumbnail()
        );
        return UsersDto.Response.toDto(user);
    }

    @Override
    public Void delete(UsersDto.Update updateDto) {
        Users userId = updateDto.toDelete();
        Users user = memberRepository.findOne(userId.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        user.deleteMemberInfo(userId.getStatus());
        return null;
    }
}
