package com.kh.reactbackend.controller;

import com.kh.reactbackend.dto.UsersDto;
import com.kh.reactbackend.entity.Users;
import com.kh.reactbackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    //회원가입
    @PostMapping
    public ResponseEntity<Void> addMember(@RequestBody UsersDto.Create createDto) {
        return ResponseEntity.ok(memberService.createMember(createDto));
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<UsersDto.Response> login(@RequestBody UsersDto.Login loginDto) {
        return ResponseEntity.ok(memberService.login(loginDto));
    }

    //회원정보조회
    @GetMapping("/{id}")
    public ResponseEntity<UsersDto.Response> getMember(@PathVariable String id) {
        return ResponseEntity.ok(memberService.selectMember(id));
    }

    //회원정보수정
    @PatchMapping("/update")
    public ResponseEntity<UsersDto.Response> updateMember(@RequestBody UsersDto.Update updateDto) {
        return ResponseEntity.ok(memberService.update(updateDto));
    }

    //회원탈퇴
    @PatchMapping("/delete")
    public ResponseEntity<Void> deleteMember(@RequestBody UsersDto.Update deleteDto){
        return ResponseEntity.ok(memberService.delete(deleteDto));
    }
}
