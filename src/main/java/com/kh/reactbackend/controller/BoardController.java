package com.kh.reactbackend.controller;

import com.kh.reactbackend.dto.BoardDto;
import com.kh.reactbackend.dto.PageResponse;
import com.kh.reactbackend.entity.Board;
import com.kh.reactbackend.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
@CrossOrigin
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Long> createBoard(@RequestBody BoardDto.Write boardDto){
        return ResponseEntity.ok(boardService.createBoard(boardDto));
    }

    @GetMapping
    public ResponseEntity<PageResponse<BoardDto.Response>> getBoards(
            @PageableDefault(size = 5 , sort = "createDate", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        return ResponseEntity.ok(new PageResponse<>(boardService.getBoardList(pageable)));
    }

}
