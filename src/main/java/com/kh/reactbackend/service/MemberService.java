package com.kh.reactbackend.service;


import com.kh.reactbackend.dto.UsersDto;

public interface MemberService {
    Void createMember(UsersDto.Create createDto);
    UsersDto.Response login(UsersDto.Login loginDto);
    UsersDto.Response update(UsersDto.Update updateDto);
    Void delete(UsersDto.Update updateDto);
    UsersDto.Response selectMember(String id);
}
