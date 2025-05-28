package com.kh.reactbackend.controller;

import com.kh.reactbackend.dto.BoardDto;
import com.kh.reactbackend.dto.ReplyDto;
import com.kh.reactbackend.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reply")
@CrossOrigin
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping
    public ResponseEntity<Long> createReply(@RequestBody ReplyDto.Content replyDto){
        return ResponseEntity.ok( replyService.createReply(replyDto));
    }

    @GetMapping("/{boardNo}")
    public ResponseEntity<List<ReplyDto.Response>> getReply(@PathVariable Long boardNo){
        return ResponseEntity.ok(replyService.findByBoardNo(boardNo));
    }
}
